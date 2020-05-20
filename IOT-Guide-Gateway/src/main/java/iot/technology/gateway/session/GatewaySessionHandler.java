package iot.technology.gateway.session;

import com.google.gson.JsonElement;
import iot.technology.gateway.adaptors.JsonMqttAdaptor;
import iot.technology.tsl.adaptor.AdaptorException;
import io.netty.handler.codec.mqtt.MqttPublishMessage;

import org.springframework.util.StringUtils;

/**
 * @author james mu
 * @date 2019/8/22 上午11:17
 */
public class GatewaySessionHandler {
    private static final String DEVICE_PROPERTY = "device";
    private static final String DEFAULT_DEVICE_TYPE = "default";


    public void onDeviceTelemetry(MqttPublishMessage mqttMsg) {

    }
    public void onDeviceConnect(MqttPublishMessage msg) throws AdaptorException {
        JsonElement json = getJson(msg);
        String deviceName = checkDeviceName(getDeviceName(json));
        System.out.println("onDeviceConnect: "+ deviceName);
    }

    public void onDeviceDisconnect(MqttPublishMessage msg) throws AdaptorException {
        String deviceName = checkDeviceName(getDeviceName(getJson(msg)));
    }

    public void onDeviceAttributes(MqttPublishMessage mqttMsg) {

    }

    public void onDeviceAttributesRequest(MqttPublishMessage msg) {

    }

    public void onDeviceRpcResponse(MqttPublishMessage mqttMsg) {

    }

    private JsonElement getJson(MqttPublishMessage mqttMsg) throws AdaptorException {
        return JsonMqttAdaptor.validateJsonPayload(mqttMsg.payload());
    }


    private String checkDeviceName(String deviceName) {
        if (StringUtils.isEmpty(deviceName)) {
            throw new RuntimeException("Device name is empty!");
        } else {
            return deviceName;
        }
    }

    private String getDeviceName(JsonElement json) throws AdaptorException {
        return json.getAsJsonObject().get(DEVICE_PROPERTY).getAsString();
    }
}
