package iot.technology.tsl.data.kv;

/**
 * 时间序列KV数据
 */
public interface TsKvEntry extends KvEntry {

    long getTs();

}
