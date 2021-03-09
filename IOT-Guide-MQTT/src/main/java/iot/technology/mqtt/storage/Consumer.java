package iot.technology.mqtt.storage;

import iot.technology.mqtt.storage.msg.QueueMsg;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author james mu
 * @date 2020/8/31 11:23
 */
@Slf4j
public class Consumer<T extends QueueMsg> {
    private final InMemoryStorage storage = InMemoryStorage.getInstance();
    private volatile Set<String> topics;
    private volatile boolean stopped;
    private volatile boolean subscribed;
    private final String topic;

    public Consumer(String topic) {
        this.topic = topic;
        stopped = false;
    }

    public String getTopic() {
        return topic;
    }

    public void subscribe() {
        topics = Collections.singleton(topic);
        subscribed = true;
    }

    public void subscribe(Set<String> topics) {
        this.topics = topics;
        subscribed = true;
    }

    public void unsubscribe() {
        stopped = true;
    }

    public List<T> poll(long durationInMillis) {
        if (subscribed) {
            List<T> messages = topics
                    .stream()
                    .map(storage::get)
                    .flatMap(List::stream)
                    .map(msg -> (T) msg).collect(Collectors.toList());

            if (messages.size() > 0) {
                return messages;
            }
            try {
                Thread.sleep(durationInMillis);
            } catch (InterruptedException e) {
                if (!stopped) {
                    log.error("Failed to sleep.", e);
                }
            }
        }

        return Collections.emptyList();
    }

    public void commit() {

    }
}
