package iot.technology.tsl.data.kv;

/**
 * @author jamesmsw
 * @date 2020/11/16 2:34 下午
 */
public class TsData implements Comparable<TsData> {

    private final long ts;
    private final Object value;

    public TsData(long ts, Object value) {
        super();
        this.ts = ts;
        this.value = value;
    }

    public long getTs() {
        return ts;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public int compareTo(TsData o) {
        return Long.compare(ts, o.ts);
    }
}
