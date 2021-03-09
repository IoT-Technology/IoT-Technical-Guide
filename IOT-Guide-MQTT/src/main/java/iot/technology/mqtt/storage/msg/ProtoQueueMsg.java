package iot.technology.mqtt.storage.msg;


/**
 * @author jamesmsw
 * @date 2021/2/19 2:23 下午
 */
public class ProtoQueueMsg implements QueueMsg {
    private final String key;
    private final String value;
    private final QueueMsgHeaders headers;

    public ProtoQueueMsg(String key, String value) {
        this(key, value, new DefaultQueueMsgHeaders());
    }

    public ProtoQueueMsg(String key, String value, QueueMsgHeaders headers) {
        this.key = key;
        this.value = value;
        this.headers = headers;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public QueueMsgHeaders getHeaders() {
        return headers;
    }

    @Override
    public byte[] getData() {
        return value.getBytes();
    }
}
