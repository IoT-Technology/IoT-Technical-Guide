package iot.technology.mqtt.storage.msg;

import java.util.HashMap;
import java.util.Map;

/**
 * @author james mu
 * @date 2020/7/27 21:57
 */
public class DefaultQueueMsgHeaders implements QueueMsgHeaders {

    protected final Map<String, byte[]> data = new HashMap<>();

    @Override
    public byte[] put(String key, byte[] value) {
        return data.put(key, value);
    }

    @Override
    public byte[] get(String key) {
        return data.get(key);
    }

    @Override
    public Map<String, byte[]> getData() {
        return data;
    }
}
