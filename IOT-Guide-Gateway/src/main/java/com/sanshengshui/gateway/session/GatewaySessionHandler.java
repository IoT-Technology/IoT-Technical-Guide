package com.sanshengshui.gateway.session;

import com.google.gson.JsonElement;
import com.sanshengshui.gateway.adaptors.JsonMqttAdaptor;
import com.sanshengshui.tsl.adaptor.AdaptorException;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author james mu
 * @date 2019/8/22 上午11:17
 */
@Slf4j
public class GatewaySessionHandler {
    private static final String DEVICE_PROPERTY = "device";

    public void onDeviceConnect(MqttPublishMessage msg) throws AdaptorException {
        JsonElement json = getJson(msg);
        String deviceName = checkDeviceName(getDeviceName(json));

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
