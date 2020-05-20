package iot.technology.tsl.data.kv;

import java.util.Optional;

/**
 * @Author: 穆书伟
 * @Date: 19-4-2
 * @Version 1.0
 */
public class BaseAttributeKvEntry implements AttributeKvEntry {

    private final long lastUpdateTs;
    private final KvEntry kv;

    public BaseAttributeKvEntry(KvEntry kv, long lastUpdateTs) {
        this.kv = kv;
        this.lastUpdateTs = lastUpdateTs;
    }

    @Override
    public long getLastUpdateTs() {
        return lastUpdateTs;
    }

    @Override
    public String getKey() {
        return kv.getKey();
    }

    @Override
    public DataType getDataType() {
        return kv.getDataType();
    }

    @Override
    public Optional<String> getStrValue() {
        return kv.getStrValue();
    }

    @Override
    public Optional<Long> getLongValue() {
        return kv.getLongValue();
    }

    @Override
    public Optional<Boolean> getBooleanValue() {
        return kv.getBooleanValue();
    }

    @Override
    public Optional<Double> getDoubleValue() {
        return kv.getDoubleValue();
    }

    @Override
    public String getValueAsString() {
        return kv.getValueAsString();
    }

    @Override
    public Object getValue() {
        return kv.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseAttributeKvEntry that = (BaseAttributeKvEntry) o;

        if (lastUpdateTs != that.lastUpdateTs) {
            return false;
        }
        return kv.equals(that.kv);

    }

    @Override
    public int hashCode() {
        int result = (int) (lastUpdateTs ^ (lastUpdateTs >>> 32));
        result = 31 * result + kv.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BaseAttributeKvEntry{" +
                "lastUpdateTs=" + lastUpdateTs +
                ", kv=" + kv +
                '}';
    }
}
