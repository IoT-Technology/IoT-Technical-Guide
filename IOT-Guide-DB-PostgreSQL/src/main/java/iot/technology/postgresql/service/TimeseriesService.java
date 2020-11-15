package iot.technology.postgresql.service;


import com.google.common.util.concurrent.ListenableFuture;
import iot.technology.tsl.data.kv.TsKvEntry;

import java.util.Collection;
import java.util.List;

public interface TimeseriesService {

    ListenableFuture<List<Void>> save(Integer deviceId, TsKvEntry tsKvEntry);

    ListenableFuture<List<TsKvEntry>> findLastest(Integer deviceId, Collection<String> keys);

}
