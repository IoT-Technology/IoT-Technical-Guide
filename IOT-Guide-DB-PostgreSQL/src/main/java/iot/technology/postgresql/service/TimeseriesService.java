package iot.technology.postgresql.service;


import com.google.common.util.concurrent.ListenableFuture;
import iot.technology.postgresql.model.TsKvLatest;
import iot.technology.tsl.data.kv.TsKvEntry;

import java.util.Collection;
import java.util.List;

public interface TimeseriesService {

    ListenableFuture<List<Void>> save(Integer deviceId, List<TsKvEntry> tsKvEntry, long ttl);

    List<TsKvLatest> findAllLatest(Integer deviceId);

    List<TsKvLatest> findLatest(Integer device, Collection<String> keys);

}
