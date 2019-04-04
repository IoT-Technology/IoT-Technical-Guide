package com.sanshengshui.mqtt.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.sanshengshui.tsl.adaptor.AdaptorException;
import com.sanshengshui.tsl.adaptor.JsonConverter;
import com.sanshengshui.tsl.core.TelemetryUploadRequest;
import com.sanshengshui.tsl.data.kv.AttributeKvEntry;
import com.sanshengshui.tsl.data.kv.KvEntry;
import com.sanshengshui.tsl.session.FromDeviceMsg;
import com.sanshengshui.tsl.session.SessionMsgType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttPublishMessage;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author james mu
 * @date 2019/4/4 21:54
 */
public class JsonMqttAdaptor {

    private static final Gson GSON = new Gson();
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final ByteBufAllocator ALLOCATOR = new UnpooledByteBufAllocator(false);

    public static void convertToMsg(SessionMsgType type, MqttMessage inbound) throws AdaptorException {
        switch (type) {
            case POST_TELEMETRY_REQUEST:
                convertToTelemetryUploadRequest( (MqttPublishMessage) inbound);
                break;
            case POST_ATTRIBUTES_REQUEST:
                convertToUpdateAttributesRequest((MqttPublishMessage) inbound);
                break;
        }
    }

    private static void convertToTelemetryUploadRequest(MqttPublishMessage inbound) throws AdaptorException {
        String payload = validatePayload(inbound.payload());
        try {
            Map<Long, List<KvEntry>> telemetryMaps = JsonConverter.convertToTelemetry(new JsonParser().parse(payload), inbound.variableHeader().messageId()).getData();
            for (Map.Entry<Long,List<KvEntry>> entry : telemetryMaps.entrySet()) {
                System.out.println("key= " + entry.getKey());
                for (KvEntry kvEntry: entry.getValue()) {
                    System.out.println("属性名="+kvEntry.getKey()+ " 属性值="+kvEntry.getValueAsString());
                }
            }
        } catch (IllegalStateException | JsonSyntaxException ex) {
            throw new AdaptorException(ex);
        }
    }

    private static void convertToUpdateAttributesRequest(MqttPublishMessage inbound) throws AdaptorException {
        String payload = validatePayload(inbound.payload());
        try {
            Set<AttributeKvEntry> attributeKvEntrySet =  JsonConverter.convertToAttributes(new JsonParser().parse(payload), inbound.variableHeader().messageId()).getAttributes();
            for (AttributeKvEntry attributeKvEntry : attributeKvEntrySet){
                System.out.println("属性名="+attributeKvEntry.getKey()+" 属性值="+attributeKvEntry.getValueAsString());
            }
        } catch (IllegalStateException | JsonSyntaxException ex) {
            throw new AdaptorException(ex);
        }
    }

    public static String validatePayload(ByteBuf payloadData) throws AdaptorException {
        try {
            String payload = payloadData.toString(UTF8);
            if (payload == null) {
                throw new AdaptorException(new IllegalArgumentException("Payload is empty!"));
            }
            return payload;
        } finally {
            payloadData.release();
        }
    }
}
