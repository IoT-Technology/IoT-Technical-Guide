package iot.technology.postgresql.service.impl;

import com.google.common.util.concurrent.ListenableFuture;
import iot.technology.postgresql.service.TimeseriesService;
import iot.technology.tsl.data.kv.TsKvEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author jamesmsw
 * @date 2020/11/15 2:39 下午
 */
@Slf4j
@Service("timeseriesService")
public class TimeseriesServiceImpl implements TimeseriesService {

    @Override
    public ListenableFuture<List<Void>> save(Integer deviceId, TsKvEntry tsKvEntry) {
        return null;
    }

    @Override
    public ListenableFuture<List<TsKvEntry>> findLastest(Integer deviceId, Collection<String> keys) {
        return null;
    }
}
