package iot.technology.gateway.adaptors;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import iot.technology.tsl.adaptor.AdaptorException;
import iot.technology.tsl.adaptor.JsonConverter;
import iot.technology.tsl.data.kv.AttributeKvEntry;
import iot.technology.tsl.data.kv.KvEntry;
import iot.technology.tsl.session.SessionMsgType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttPublishMessage;

import java.nio.charset.Charset;
import java.util.*;

/**
 * @author james mu
 * @date 2019/8/22 上午11:21
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
            case SUBSCRIBE_ATTRIBUTES_REQUEST:
                System.out.println("{\"key1\":\"value1\"}");
                break;
            case GET_ATTRIBUTES_REQUEST:
                convertToGetAttributesRequest((MqttPublishMessage) inbound);
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

    private static void convertToGetAttributesRequest(MqttPublishMessage inbound) throws AdaptorException {
        try {
            String payload = inbound.payload().toString(UTF8);
            JsonElement requestBody = new JsonParser().parse(payload);
            Set<String> clientKeys = toStringSet(requestBody, "clientKeys");
            Set<String> sharedKeys = toStringSet(requestBody, "sharedKeys");
            if (clientKeys == null && sharedKeys == null) {
            } else {
                for (String clientKey : clientKeys) {
                    System.out.print("客户端属性:" + clientKey +" ");
                }
                for (String sharedKey : sharedKeys) {
                    System.out.print("共享设备属性:" + sharedKey + " ");
                }
            }
        }catch (RuntimeException e) {
            throw new AdaptorException(e);
        }
    }

    private static Set<String> toStringSet(JsonElement requestBody, String name) {
        JsonElement element = requestBody.getAsJsonObject().get(name);
        if (element != null) {
            return new HashSet<>(Arrays.asList(element.getAsString().split(",")));
        } else {
            return null;
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

    public static JsonElement validateJsonPayload(ByteBuf payloadData) throws AdaptorException {
        String payload = validatePayload(payloadData);
        try {
            return new JsonParser().parse(payload);
        } catch (JsonSyntaxException ex) {
            throw new AdaptorException(ex);
        }
    }
}
