package iot.technology.tsl.data.kv;

import java.util.Objects;
import java.util.Optional;

/**
 * @Author: 穆书伟
 * @Date: 19-4-2
 * @Version 1.0
 */
public class BooleanDataEntry extends BasicKvEntry {

    private final Boolean value;

    public BooleanDataEntry(String key, Boolean value) {
        super(key);
        this.value = value;
    }

    @Override
    public DataType getDataType() {
        return DataType.BOOLEAN;
    }

    @Override
    public Optional<Boolean> getBooleanValue() {
        return Optional.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BooleanDataEntry)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        BooleanDataEntry that = (BooleanDataEntry) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    @Override
    public String toString() {
        return "BooleanDataEntry{" +
                "value=" + value +
                "} " + super.toString();
    }

    @Override
    public String getValueAsString() {
        return Boolean.toString(value);
    }
}
