package iot.technology.tsl.adaptor;

import com.google.gson.*;
import iot.technology.tsl.core.*;
import iot.technology.tsl.data.kv.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 穆书伟
 * @Date: 19-4-2
 * @Version 1.0
 */
public class JsonConverter {

    private static final Gson GSON = new Gson();
    public static final String CAN_T_PARSE_VALUE = "Can't parse value: ";

    public static TelemetryUploadRequest convertToTelemetry(JsonElement jsonObject) throws JsonSyntaxException {
        return convertToTelemetry(jsonObject, BasicRequest.DEFAULT_REQUEST_ID);
    }

    public static TelemetryUploadRequest convertToTelemetry(JsonElement jsonObject, int requestId) throws JsonSyntaxException {
        return convertToTelemetry(jsonObject, System.currentTimeMillis(), requestId);
    }

    private static TelemetryUploadRequest convertToTelemetry(JsonElement jsonObject, long systemTs, int requestId) throws JsonSyntaxException {
        BasicTelemetryUploadRequest request = new BasicTelemetryUploadRequest(requestId);
        if (jsonObject.isJsonObject()) {
            parseObject(request, systemTs, jsonObject);
        } else if (jsonObject.isJsonArray()) {
            jsonObject.getAsJsonArray().forEach(je -> {
                if (je.isJsonObject()) {
                    parseObject(request, systemTs, je.getAsJsonObject());
                } else {
                    throw new JsonSyntaxException(CAN_T_PARSE_VALUE + je);
                }
            });
        } else {
            throw new JsonSyntaxException(CAN_T_PARSE_VALUE + jsonObject);
        }
        return request;
    }

    private static void parseObject(BasicTelemetryUploadRequest request, long systemTs, JsonElement jsonObject) {
        JsonObject jo = jsonObject.getAsJsonObject();
        if (jo.has("ts") && jo.has("values")) {
            parseWithTs(request, jo);
        } else {
            parseWithoutTs(request, systemTs, jo);
        }
    }

    public static void parseWithTs(BasicTelemetryUploadRequest request, JsonObject jo) {
        long ts = jo.get("ts").getAsLong();
        JsonObject valuesObject = jo.get("values").getAsJsonObject();
        for (KvEntry entry : parseValues(valuesObject)) {
            request.add(ts, entry);
        }
    }

    private static void parseWithoutTs(BasicTelemetryUploadRequest request, long systemTs, JsonObject jo) {
        for (KvEntry entry : parseValues(jo)) {
            request.add(systemTs, entry);
        }
    }

    public static List<KvEntry> parseValues(JsonObject valuesObject) {
        List<KvEntry> result = new ArrayList<>();
        for (Map.Entry<String, JsonElement> valueEntry : valuesObject.entrySet()) {
            JsonElement element = valueEntry.getValue();
            if (element.isJsonPrimitive()) {
                JsonPrimitive value = element.getAsJsonPrimitive();
                if (value.isString()) {
                    result.add(new StringDataEntry(valueEntry.getKey(), value.getAsString()));
                } else if (value.isBoolean()) {
                    result.add(new BooleanDataEntry(valueEntry.getKey(), value.getAsBoolean()));
                } else if (value.isNumber()) {
                    parseNumericValue(result, valueEntry, value);
                } else {
                    throw new JsonSyntaxException(CAN_T_PARSE_VALUE + value);
                }
            } else {
                throw new JsonSyntaxException(CAN_T_PARSE_VALUE + element);
            }
        }
        return result;
    }

    private static void parseNumericValue(List<KvEntry> result, Map.Entry<String, JsonElement> valueEntry, JsonPrimitive value) {
        if (value.getAsString().contains(".")) {
            result.add(new DoubleDataEntry(valueEntry.getKey(), value.getAsDouble()));
        } else {
            try {
                long longValue = Long.parseLong(value.getAsString());
                result.add(new LongDataEntry(valueEntry.getKey(), longValue));
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException("Big integer values are not supported!");
            }
        }
    }


    public static AttributesUpdateRequest convertToAttributes(JsonElement element) {
        return convertToAttributes(element, BasicRequest.DEFAULT_REQUEST_ID);
    }

    public static AttributesUpdateRequest convertToAttributes(JsonElement element, int requestId) {
        if (element.isJsonObject()) {
            BasicAttributesUpdateRequest request = new BasicAttributesUpdateRequest(requestId);
            long ts = System.currentTimeMillis();
            request.add(parseValues(element.getAsJsonObject()).stream().map(kv -> new BaseAttributeKvEntry(kv, ts)).collect(Collectors.toList()));
            return request;
        } else {
            throw new JsonSyntaxException(CAN_T_PARSE_VALUE + element);
        }
    }
}
