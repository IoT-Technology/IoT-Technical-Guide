package iot.technology.mqtt.storage;

import iot.technology.mqtt.storage.msg.QueueMsg;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author james mu
 * @date 2020/8/31 11:05
 */
@Data
@Slf4j
public class Producer<T extends QueueMsg> {

    private final InMemoryStorage storage = InMemoryStorage.getInstance();

    private final String defaultTopic;

    public Producer(String defaultTopic) {
        this.defaultTopic = defaultTopic;
    }

    public void init() {

    }

    public void send(String topicName, T msg) {
        log.info("topic: {}, msg: {}", topicName, msg);
        boolean result = storage.put(topicName, msg);
//        if (result) {
//            if (callback != null) {
//                callback.onSuccess(null);
//            }
//        } else {
//            if (callback != null) {
//                callback.onFailure(new RuntimeException("Failure add msg to InMemoryQueue"));
//            }
//        }
    }

    public void stop() {
    }

}
