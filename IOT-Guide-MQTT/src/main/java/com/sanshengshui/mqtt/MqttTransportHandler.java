package com.sanshengshui.mqtt;

import com.sanshengshui.mqtt.adapter.JsonMqttAdaptor;
import com.sanshengshui.tsl.adaptor.AdaptorException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.mqtt.*;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.sanshengshui.mqtt.MqttTopics.DEVICE_ATTRIBUTES_TOPIC;
import static com.sanshengshui.tsl.session.SessionMsgType.POST_ATTRIBUTES_REQUEST;
import static com.sanshengshui.tsl.session.SessionMsgType.POST_TELEMETRY_REQUEST;
import static io.netty.handler.codec.mqtt.MqttMessageType.*;
import static io.netty.handler.codec.mqtt.MqttQoS.*;

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
                JsonMqttAdaptor.convertToMsg(POST_TELEMETRY_REQUEST, mqttMsg);
            } else if(topicName.equals(DEVICE_ATTRIBUTES_TOPIC)) {
                JsonMqttAdaptor.convertToMsg(POST_ATTRIBUTES_REQUEST, mqttMsg);
            }
        } catch (AdaptorException e) {

        }

    }

    private void processSubscribe(ChannelHandlerContext ctx, MqttSubscribeMessage mqttMsg) {
        if (!checkConnected(ctx)) {
            return;
        }
        List<Integer> grantedQoSList = new ArrayList<>();
        for (MqttTopicSubscription subscription : mqttMsg.payload().topicSubscriptions()) {
            String topicName = subscription.topicName();
            //TODO: handle this qos level.
            MqttQoS reqQoS = subscription.qualityOfService();
            if (topicName.equals(DEVICE_ATTRIBUTES_TOPIC)) {

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

    private void processUnsubscribe(ChannelHandlerContext ctx, MqttUnsubscribeMessage mqttMsg) {
        if (!checkConnected(ctx)) {
            return;
        }
        for (String topicName: mqttMsg.payload().topics()) {
            switch (topicName) {

            }
        }
    }

    private void processDisconnect(ChannelHandlerContext ctx) {
        ctx.close();
    }

    private void processConnect(ChannelHandlerContext ctx, MqttConnectMessage msg) {
        ctx.writeAndFlush(createMqttConnAckMsg(MqttConnectReturnCode.CONNECTION_ACCEPTED));
        connected = true;
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
