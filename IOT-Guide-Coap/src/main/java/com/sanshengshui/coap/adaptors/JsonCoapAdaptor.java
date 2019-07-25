package com.sanshengshui.coap.adaptors;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.sanshengshui.coap.session.SessionMsgType;
import com.sanshengshui.tsl.adaptor.AdaptorException;
import com.sanshengshui.tsl.adaptor.JsonConverter;
import com.sanshengshui.tsl.data.kv.AttributeKvEntry;
import com.sanshengshui.tsl.data.kv.KvEntry;
import org.eclipse.californium.core.coap.Request;
import org.springframework.util.StringUtils;

import java.util.*;

public class JsonCoapAdaptor {

    public static void convertToMsg(SessionMsgType type, Request inbound) throws AdaptorException{
        switch (type) {
            case POST_ATTRIBUTES_REQUEST:
                convertToUpdateAttributesRequest(inbound);
            case POST_TELEMETRY_REQUEST:
                convertToTelemetryUploadRequest(inbound);
            case GET_ATTRIBUTES_REQUEST:
                convertToGetAttributesRequest(inbound);
                break;
        }
    }

    private static void convertToUpdateAttributesRequest(Request inbound) throws AdaptorException {
        String payload = validatePayload(inbound);
        try {
            Set<AttributeKvEntry> attributeKvEntrySet =  JsonConverter.convertToAttributes(new JsonParser().parse(payload)).getAttributes();
            for (AttributeKvEntry attributeKvEntry : attributeKvEntrySet){
                System.out.println("属性名="+attributeKvEntry.getKey()+" 属性值="+attributeKvEntry.getValueAsString());
            }
        } catch (IllegalStateException | JsonSyntaxException ex) {
            throw new AdaptorException(ex);
        }
    }

    private static void convertToTelemetryUploadRequest(Request inbound) throws AdaptorException{
        String payload = validatePayload(inbound);
        try {
            Map<Long, List<KvEntry>> telemetryMaps = JsonConverter.convertToTelemetry(new JsonParser().parse(payload)).getData();
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

    private static void convertToGetAttributesRequest(Request inbound) throws AdaptorException{
        List<String> queryElements = inbound.getOptions().getUriQuery();
        Set<String> clientKeys = toKeys(queryElements, "clientKeys");
        Set<String> sharedKeys = toKeys(queryElements, "sharedKeys");
        if (clientKeys == null && sharedKeys == null) {
        } else {
            for (String clientKey : clientKeys) {
                System.out.print("客户端属性:" + clientKey +" ");
            }
            for (String sharedKey : sharedKeys) {
                System.out.print("共享设备属性:" + sharedKey + " ");
            }
        }
    }

    private static Set<String> toKeys(List<String> queryElements, String attributeName) throws AdaptorException {
        String keys = null;
        for (String queryElement : queryElements) {
            String[] queryItem = queryElement.split("=");
            if (queryItem.length == 2 && queryItem[0].equals(attributeName)) {
                keys = queryItem[1];
            }
        }
        if (keys != null && !StringUtils.isEmpty(keys)) {
            return new HashSet<>(Arrays.asList(keys.split(",")));
        } else {
            return null;
        }
    }

    private static String validatePayload(Request inbound) throws AdaptorException{
        String payload = inbound.getPayloadString();
        if (payload == null) {
            throw new AdaptorException(new IllegalArgumentException("Payload is empty!"));
        }
        return payload;
    }
}
