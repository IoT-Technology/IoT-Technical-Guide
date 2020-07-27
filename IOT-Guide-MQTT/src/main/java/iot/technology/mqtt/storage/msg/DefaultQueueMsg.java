package iot.technology.mqtt.storage.msg;

import lombok.Data;

/**
 * @author james mu
 * @date 2020/7/27 21:59
 */
@Data
public class DefaultQueueMsg implements QueueMsg {
    private final String key;
    private final byte[] data;
    private final DefaultQueueMsgHeaders headers;

    public DefaultQueueMsg(QueueMsg msg) {
        this.key = msg.getKey();
        this.data = msg.getData();
        DefaultQueueMsgHeaders headers = new DefaultQueueMsgHeaders();
        msg.getHeaders().getData().forEach(headers::put);
        this.headers = headers;
    }

}
