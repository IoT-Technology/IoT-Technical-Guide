package iot.technology.mqtt.storage;

import iot.technology.mqtt.storage.msg.QueueMsg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author james mu
 * @date 2020/7/27 21:47
 */
public final class InMemoryStorage {

    private static InMemoryStorage instance;
    private final ConcurrentHashMap<String, BlockingDeque<QueueMsg>> storage;

    private InMemoryStorage() {
        storage = new ConcurrentHashMap<>();
    }

    public static InMemoryStorage getInstance() {
        if (instance == null) {
            synchronized (InMemoryStorage.class) {
                if (instance == null) {
                    instance = new InMemoryStorage();
                }
            }
        }
        return instance;
    }

    public boolean put(String topic, QueueMsg msg) {
        return storage.computeIfAbsent(topic, (t) -> new LinkedBlockingDeque<>()).add(msg);
    }

    public <T extends QueueMsg> List<T> get(String topic) {
        if (storage.containsKey(topic)) {
            List<T> entities;
            T first = (T) storage.get(topic).poll();
            if (first != null) {
                entities = new ArrayList<>();
                entities.add(first);
                List<QueueMsg> otherList = new ArrayList<>();
                storage.get(topic).drainTo(otherList, 999);
                for (QueueMsg other : otherList) {
                    entities.add((T) other);
                }
            } else {
                entities = Collections.emptyList();
            }
        }
        return Collections.emptyList();
    }

    public void cleanup() {
        storage.clear();
    }
}
