package iot.technology.mqtt.storage;

import iot.technology.mqtt.storage.msg.QueueMsg;
import iot.technology.mqtt.storage.queue.QueueCallback;
import iot.technology.mqtt.storage.queue.TopicPartitionInfo;

/**
 * @author james mu
 * @date 2020/8/31 11:05
 */
public class Producer<T extends QueueMsg> {

    private final InMemoryStorage storage = InMemoryStorage.getInstance();

    private final String defaultTopic;

    public Producer(String defaultTopic) {
        this.defaultTopic = defaultTopic;
    }

    public void init() {

    }

    public void send(TopicPartitionInfo tpi, T msg, QueueCallback callback) {
        boolean result = storage.put(tpi.getFullTopicName(), msg);
        if (result) {
            if (callback != null) {
                callback.onSuccess(null);
            }
        } else {
            if (callback != null) {
                callback.onFailure(new RuntimeException("Failure add msg to InMemoryQueue"));
            }
        }
    }

    public void stop() {

    }

}
