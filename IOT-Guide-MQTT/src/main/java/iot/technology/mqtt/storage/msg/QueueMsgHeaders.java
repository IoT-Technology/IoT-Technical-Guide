package iot.technology.mqtt.storage.msg;

import java.util.Map;

/**
 * @author james mu
 * @date 2020/7/27 21:55
 */
public interface QueueMsgHeaders {

    byte[] put(String key, byte[] value);

    byte[] get(String key);

    Map<String, byte[]> getData();
}
