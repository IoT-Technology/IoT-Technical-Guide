package iot.technology.mqtt;

/**
 * @Author: 穆书伟
 * @Date: 19-4-3 下午3:11
 * @Version 1.0
 */
public class MqttTopics {
    
    public static final String BASE_DEVICE_API_TOPIC = "v1/devices/me";
    public static final String DEVICE_TELEMETRY_TOPIC = BASE_DEVICE_API_TOPIC + "/telemetry";
    public static final String DEVICE_ATTRIBUTES_TOPIC = BASE_DEVICE_API_TOPIC + "/attributes";
    public static final String DEVICE_ATTRIBUTES_REQUEST_TOPIC_PREFIX = BASE_DEVICE_API_TOPIC + "/attributes/request/";

    public static final String DEVICE_ATTRIBUTES_RESPONSE_TOPIC_PREFIX = BASE_DEVICE_API_TOPIC + "/attributes/response/";
    public static final String DEVICE_ATTRIBUTES_RESPONSES_TOPIC = DEVICE_ATTRIBUTES_RESPONSE_TOPIC_PREFIX + "+";
}
