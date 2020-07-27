package iot.technology.mqtt.storage.msg;

/**
 * @author james mu
 * @date 2020/7/27 22:00
 */
public interface QueueMsg {

    String getKey();

    QueueMsgHeaders getHeaders();

    byte[] getData();
}
