package iot.technology.tsl.data.kv;

import java.util.Objects;
import java.util.Optional;

/**
 * @Author: 穆书伟
 * @Date: 19-4-2
 * @Version 1.0
 */
public abstract class BasicKvEntry implements KvEntry {

    private final String key;

    protected BasicKvEntry(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Optional<String> getStrValue() {
        return Optional.ofNullable(null);
    }

    @Override
    public Optional<Long> getLongValue() {
        return Optional.ofNullable(null);
    }

    @Override
    public Optional<Boolean> getBooleanValue() {
        return Optional.ofNullable(null);
    }

    @Override
    public Optional<Double> getDoubleValue() {
        return Optional.ofNullable(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BasicKvEntry)) {
            return false;
        }
        BasicKvEntry that = (BasicKvEntry) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "BasicKvEntry{" +
                "key='" + key + '\'' +
                '}';
    }
}
