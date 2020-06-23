package iot.technology.mqtt;

import iot.technology.mqtt.adapter.JsonMqttAdaptor;
import iot.technology.tsl.adaptor.AdaptorException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.mqtt.*;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static iot.technology.mqtt.MqttTopics.*;
import static io.netty.handler.codec.mqtt.MqttMessageType.*;
import static io.netty.handler.codec.mqtt.MqttQoS.*;
import static iot.technology.tsl.session.SessionMsgType.*;

/**
 * @Author: 穆书伟
 * @Date: 19-4-3 下午3:35
 * @Version 1.0
 */
public class MqttTransportHandler extends ChannelInboundHandlerAdapter implements GenericFutureListener<Future<? super Void>> {

    public static final MqttQoS MAX_SUPPORTED_QOS_LVL = MqttQoS.AT_LEAST_ONCE;

    private volatile boolean connected;
    private volatile InetSocketAddress address;
    private final ConcurrentMap<MqttTopicMatcher,Integer> mqttQoSMap;

    public MqttTransportHandler() {
        this.mqttQoSMap = new ConcurrentHashMap<>();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof MqttMessage) {
            processMqttMsg(ctx,(MqttMessage) msg);
        } else {
            ctx.close();
        }
    }

    private void processMqttMsg(ChannelHandlerContext ctx, MqttMessage msg) {
        address = (InetSocketAddress) ctx.channel().remoteAddress();
        if (msg.fixedHeader() == null) {
            processDisconnect(ctx);
            return;
        }

        switch (msg.fixedHeader().messageType()) {
            case CONNECT:
                processConnect(ctx, (MqttConnectMessage) msg);
                break;
            case PUBLISH:
                processPublish(ctx, (MqttPublishMessage) msg);
                break;
            case SUBSCRIBE:
                processSubscribe(ctx, (MqttSubscribeMessage) msg);
                break;
            case UNSUBSCRIBE:
                processUnsubscribe(ctx, (MqttUnsubscribeMessage) msg);
                break;
            case PINGREQ:
                if (checkConnected(ctx)) {
                    ctx.writeAndFlush(new MqttMessage(new MqttFixedHeader(PINGRESP,false,AT_MOST_ONCE, false, 0)));
                }
                break;
            case DISCONNECT:
                if (checkConnected(ctx)) {
                    processDisconnect(ctx);
                }
                break;
            default:
                break;

        }
    }

    private void processPublish(ChannelHandlerContext ctx, MqttPublishMessage mqttMsg) {

        if (!checkConnected(ctx)) {
            return;
        }

        String topicName = mqttMsg.variableHeader().topicName();
        int msgId = mqttMsg.variableHeader().messageId();
        processDevicePublish(ctx, mqttMsg, topicName, msgId);

    }

    private void processDevicePublish(ChannelHandlerContext ctx, MqttPublishMessage mqttMsg, String topicName, int msgId) {
        try {
            if (topicName.equals(MqttTopics.DEVICE_TELEMETRY_TOPIC)) {
                System.out.println(JsonMqttAdaptor.validatePayload(mqttMsg.payload()));
            } else if(topicName.equals(DEVICE_ATTRIBUTES_TOPIC)) {
                JsonMqttAdaptor.convertToMsg(POST_ATTRIBUTES_REQUEST, mqttMsg);
            } else if(topicName.equals(MqttTopics.DEVICE_ATTRIBUTES_REQUEST_TOPIC_PREFIX)) {
                JsonMqttAdaptor.convertToMsg(GET_ATTRIBUTES_REQUEST, mqttMsg);
            }
            ctx.writeAndFlush(createMqttPubAckMsg(msgId));
        } catch (AdaptorException e) {
            ctx.close();
        }

    }

    private void processSubscribe(ChannelHandlerContext ctx, MqttSubscribeMessage mqttMsg) {
        if (!checkConnected(ctx)) {
            return;
        }
        List<Integer> grantedQoSList = new ArrayList<>();
        for (MqttTopicSubscription subscription : mqttMsg.payload().topicSubscriptions()) {
            String topic = subscription.topicName();
            MqttQoS reqQoS = subscription.qualityOfService();
            try {
                switch (topic) {
                    case DEVICE_ATTRIBUTES_TOPIC: {
                        JsonMqttAdaptor.convertToMsg(SUBSCRIBE_ATTRIBUTES_REQUEST, mqttMsg);
                        registerSubQoS(topic, grantedQoSList, reqQoS);
                        break;
                    }
                    case DEVICE_ATTRIBUTES_RESPONSE_TOPIC_PREFIX:
                        registerSubQoS(topic, grantedQoSList, reqQoS);
                        break;
                    default:
                        grantedQoSList.add(FAILURE.value());
                        break;
                }
            } catch (AdaptorException e) {
                grantedQoSList.add(FAILURE.value());
            }
        }
        ctx.writeAndFlush(createSubAckMessage(mqttMsg.variableHeader().messageId(), grantedQoSList));
    }

    private static MqttSubAckMessage createSubAckMessage(Integer msgId, List<Integer> grantedQoSList) {
        MqttFixedHeader mqttFixedHeader =
                new MqttFixedHeader(SUBACK, false, AT_LEAST_ONCE, false, 0);
        MqttMessageIdVariableHeader mqttMessageIdVariableHeader = MqttMessageIdVariableHeader.from(msgId);
        MqttSubAckPayload mqttSubAckPayload = new MqttSubAckPayload(grantedQoSList);
        return new MqttSubAckMessage(mqttFixedHeader, mqttMessageIdVariableHeader, mqttSubAckPayload);
    }

    private void registerSubQoS(String topic, List<Integer> grantedQoSList, MqttQoS reqQoS) {
        grantedQoSList.add(getMinSupportedQos(reqQoS));
        mqttQoSMap.put(new MqttTopicMatcher(topic), getMinSupportedQos(reqQoS));
    }

    private static int getMinSupportedQos(MqttQoS reqQoS) {
        return Math.min(reqQoS.value(), MAX_SUPPORTED_QOS_LVL.value());
    }

    private void processUnsubscribe(ChannelHandlerContext ctx, MqttUnsubscribeMessage mqttMsg) {
        if (!checkConnected(ctx)) {
            return;
        }
        for (String topicName: mqttMsg.payload().topics()) {
            mqttQoSMap.remove(new MqttTopicMatcher(topicName));
            try {
                switch (topicName) {
                    case DEVICE_ATTRIBUTES_TOPIC: {
                        JsonMqttAdaptor.convertToMsg(UNSUBSCRIBE_ATTRIBUTES_REQUEST, mqttMsg);
                        break;
                    }
                    case DEVICE_ATTRIBUTES_RESPONSES_TOPIC:
                        break;

                }
            }catch (AdaptorException e) {

            }
        }
        ctx.writeAndFlush(createUnSubAckMessage(mqttMsg.variableHeader().messageId()));
    }

    private void processDisconnect(ChannelHandlerContext ctx) {
        ctx.close();
    }

    private void processConnect(ChannelHandlerContext ctx, MqttConnectMessage msg) {
        ctx.writeAndFlush(createMqttConnAckMsg(MqttConnectReturnCode.CONNECTION_ACCEPTED));
        connected = true;
    }

    private static MqttPubAckMessage createMqttPubAckMsg(int requestId) {
        MqttFixedHeader mqttFixedHeader =
                new MqttFixedHeader(PUBACK, false, AT_LEAST_ONCE, false, 0);
        MqttMessageIdVariableHeader mqttMsgIdVariableHeader =
                MqttMessageIdVariableHeader.from(requestId);
        return new MqttPubAckMessage(mqttFixedHeader, mqttMsgIdVariableHeader);
    }

    private MqttMessage createUnSubAckMessage(int msgId) {
        MqttFixedHeader mqttFixedHeader =
                new MqttFixedHeader(UNSUBACK, false, AT_LEAST_ONCE, false, 0);
        MqttMessageIdVariableHeader mqttMessageIdVariableHeader = MqttMessageIdVariableHeader.from(msgId);
        return new MqttMessage(mqttFixedHeader, mqttMessageIdVariableHeader);
    }

    private MqttConnAckMessage createMqttConnAckMsg(MqttConnectReturnCode returnCode) {
        MqttFixedHeader mqttFixedHeader =
                new MqttFixedHeader(CONNACK, false, AT_MOST_ONCE, false, 0);
        MqttConnAckVariableHeader mqttConnAckVariableHeader =
                new MqttConnAckVariableHeader(returnCode, true);
        return new MqttConnAckMessage(mqttFixedHeader, mqttConnAckVariableHeader);
    }

    private boolean checkConnected(ChannelHandlerContext ctx) {
        if (connected) {
            return  true;
        } else {
            ctx.close();
            return false;
        }
    }

    @Override
    public void operationComplete(Future<? super Void> future) throws Exception {

    }
}
