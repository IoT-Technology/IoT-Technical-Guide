package iot.technology.gateway;

/**
 * @author james mu
 * @date 2019/8/22 下午2:21
 */
public class MqttTopics {

    public static final String BASE_GATEWAY_API_TOPIC = "v1/gateway";
    public static final String GATEWAY_CONNECT_TOPIC = BASE_GATEWAY_API_TOPIC + "/connect";
    public static final String GATEWAY_DISCONNECT_TOPIC = BASE_GATEWAY_API_TOPIC + "/disconnect";
    public static final String GATEWAY_ATTRIBUTES_TOPIC = BASE_GATEWAY_API_TOPIC + "/attributes";
    public static final String GATEWAY_TELEMETRY_TOPIC = BASE_GATEWAY_API_TOPIC + "/telemetry";
    public static final String GATEWAY_RPC_TOPIC = BASE_GATEWAY_API_TOPIC + "/rpc";
    public static final String GATEWAY_ATTRIBUTES_REQUEST_TOPIC = BASE_GATEWAY_API_TOPIC + "/attributes/request";
    public static final String GATEWAY_ATTRIBUTES_RESPONSE_TOPIC = BASE_GATEWAY_API_TOPIC + "/attributes/response";

    private MqttTopics() {
    }
}
