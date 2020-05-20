package iot.technology.tsl.data.kv;

import java.util.Objects;
import java.util.Optional;

/**
 * @Author: 穆书伟
 * @Date: 19-4-2
 * @Version 1.0
 */
public class DoubleDataEntry extends BasicKvEntry {

    private final Double value;

    public DoubleDataEntry(String key, Double value) {
        super(key);
        this.value = value;
    }

    @Override
    public DataType getDataType() {
        return DataType.DOUBLE;
    }

    @Override
    public Optional<Double> getDoubleValue() {
        return Optional.of(value);
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DoubleDataEntry)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        DoubleDataEntry that = (DoubleDataEntry) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }

    @Override
    public String toString() {
        return "DoubleDataEntry{" +
                "value=" + value +
                "} " + super.toString();
    }

    @Override
    public String getValueAsString() {
        return Double.toString(value);
    }
}
