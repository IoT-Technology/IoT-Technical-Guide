package iot.technology.gateway;

import iot.technology.gateway.session.GatewaySessionHandler;
import iot.technology.tsl.adaptor.AdaptorException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.mqtt.*;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.net.InetSocketAddress;

import static io.netty.handler.codec.mqtt.MqttMessageType.CONNACK;
import static io.netty.handler.codec.mqtt.MqttMessageType.PINGRESP;
import static io.netty.handler.codec.mqtt.MqttQoS.AT_MOST_ONCE;

/**
 * @author james mu
 * @date 2019/8/22 上午10:41
 */
public class GatewayTransportHandler extends ChannelInboundHandlerAdapter implements GenericFutureListener<Future<? super Void>> {

    public static final MqttQoS MAX_SUPPORTED_QOS_LVL = MqttQoS.AT_LEAST_ONCE;

    private volatile GatewaySessionHandler gatewaySessionHandler;

    private volatile boolean connected;
    private volatile InetSocketAddress address;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg instanceof MqttMessage) {
                processMqttMsg(ctx,(MqttMessage) msg);
            } else {
                ctx.close();
            }
        } finally {
            ReferenceCountUtil.safeRelease(msg);
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

    private void processDisconnect(ChannelHandlerContext ctx) {
        ctx.close();
    }

    private void processConnect(ChannelHandlerContext ctx, MqttConnectMessage msg) {
        ctx.writeAndFlush(createMqttConnAckMsg(MqttConnectReturnCode.CONNECTION_ACCEPTED));
        connected = true;
    }

    private void processPublish(ChannelHandlerContext ctx, MqttPublishMessage mqttMsg) {

        if (!checkConnected(ctx)) {
            return;
        }
        String topicName = mqttMsg.variableHeader().topicName();
        int msgId = mqttMsg.variableHeader().messageId();
        handleGatewayPublishMsg(topicName, msgId, mqttMsg);

    }

    private void processSubscribe(ChannelHandlerContext ctx, MqttSubscribeMessage mqttMsg) {
        if (!checkConnected(ctx)) {
            return;
        }
    }

    private void processUnsubscribe(ChannelHandlerContext ctx, MqttUnsubscribeMessage mqttMsg) {
        if (!checkConnected(ctx)) {

        }
    }

    private void handleGatewayPublishMsg(String topicName, int msgId, MqttPublishMessage mqttMsg) {
        try {
            switch (topicName) {
                case MqttTopics.GATEWAY_TELEMETRY_TOPIC:
                    gatewaySessionHandler.onDeviceTelemetry(mqttMsg);
                    break;
                case MqttTopics.GATEWAY_ATTRIBUTES_TOPIC:
                    gatewaySessionHandler.onDeviceAttributes(mqttMsg);
                    break;
                case MqttTopics.GATEWAY_ATTRIBUTES_REQUEST_TOPIC:
                    gatewaySessionHandler.onDeviceAttributesRequest(mqttMsg);
                    break;
                case MqttTopics.GATEWAY_RPC_TOPIC:
                    gatewaySessionHandler.onDeviceRpcResponse(mqttMsg);
                    break;
                case MqttTopics.GATEWAY_CONNECT_TOPIC:
                    gatewaySessionHandler.onDeviceConnect(mqttMsg);
                    break;
                case MqttTopics.GATEWAY_DISCONNECT_TOPIC:
                    gatewaySessionHandler.onDeviceDisconnect(mqttMsg);
                    break;
            }
        } catch (RuntimeException | AdaptorException e) {

        }
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
