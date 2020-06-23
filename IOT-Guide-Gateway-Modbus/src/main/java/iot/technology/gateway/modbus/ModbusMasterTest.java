package iot.technology.gateway.modbus;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.locator.BaseLocator;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author james mu
 * @date 2020/6/20 21:09
 */
@Slf4j
public class ModbusMasterTest {

    /**
     * 设置modbus点位
     *
     * @return BatchRead<String>
     */
    private static BatchRead<String> batch() {
        BatchRead<String> batch = new BatchRead<>();
        batch.addLocator("1", BaseLocator.holdingRegister(1,0, DataType.TWO_BYTE_INT_SIGNED));
        batch.addLocator("2", BaseLocator.holdingRegister(1, 1, DataType.TWO_BYTE_INT_SIGNED));
        batch.addLocator("3", BaseLocator.holdingRegister(1, 2, DataType.TWO_BYTE_INT_SIGNED));
        batch.addLocator("4", BaseLocator.holdingRegister(1, 3, DataType.TWO_BYTE_INT_SIGNED));
        return batch;
    }


    public static void main(String[] args) throws MqttException {
        /**
         * ModBusMaster主要读取类
         */
        ModbusMaster modbusMaster = ModbusUtil.createMaster("127.0.0.1", 502);

        final String serverUrl = "tcp://127.0.0.1:1883";
        final String clientId = "mqtt_client_id";
        final String deviceName = "mqtt_client_name";

        final MqttClient client = new MqttClient(serverUrl, clientId, null);
        client.connect();

        log.info("The device " + deviceName + " has been registered successfully!");

        Executors.newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    try {
                        BatchResults<String> results = ModbusUtil.batchRead(modbusMaster, batch());
                        log.info("slave id 1 读取结果为: " + results);
                        client.publish("v1/devices/me/telemetry", new MqttMessage(String.valueOf(results).getBytes()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        }, 1, 7, TimeUnit.SECONDS);

    }
}
