package iot.technology.postgresql.service.impl;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import iot.technology.postgresql.dao.TimeseriesDao;
import iot.technology.postgresql.dao.TimeseriesLatestDao;
import iot.technology.postgresql.model.TsKv;
import iot.technology.postgresql.model.TsKvLatest;
import iot.technology.postgresql.model.mapping.TsKvLatestMapper;
import iot.technology.postgresql.model.mapping.TsKvMapper;
import iot.technology.postgresql.service.TimeseriesService;
import iot.technology.tsl.data.kv.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author jamesmsw
 * @date 2020/11/15 2:39 下午
 */
@Slf4j
@Service("timeseriesService")
public class TimeseriesServiceImpl implements TimeseriesService {

    private static final int INSERTS_PER_ENTRY = 3;

    @Resource(name = "timeseriesLatestDao")
    private TimeseriesLatestDao timeseriesLatestDao;
    @Resource(name = "timeseriesDao")
    private TimeseriesDao timeseriesDao;

    @Override
    public ListenableFuture<List<Void>> save(Integer deviceId, List<TsKvEntry> tsKvEntry, long ttl) {
        List<ListenableFuture<Void>> futures = Lists.newArrayListWithExpectedSize(tsKvEntry.size() * INSERTS_PER_ENTRY);
        for (TsKvEntry kvEntry : tsKvEntry) {
            if (tsKvEntry == null) {
                throw new RuntimeException("Key value entry can't be null");
            }
            saveLatest(deviceId, kvEntry);
            saveTimeseries(deviceId, kvEntry);

        }
        return Futures.allAsList(futures);
    }

    @Override
    public List<TsKvLatest> findAllLatest(Integer deviceId) {
        List<TsKvLatest> tsKvLatests = TsKvLatestMapper.INSTANCE.convert(timeseriesLatestDao.findAllByDeviceId(deviceId));
       return tsKvLatests;
    }

    @Override
    public List<TsKvLatest> findLatest(Integer deviceId, Collection<String> keys) {
        List<TsKvLatest> tsKvLatests = Lists.newArrayList();
        keys.forEach(key -> {
            TsKvLatest tsKvLatest = TsKvLatestMapper.INSTANCE.entityToTsKv(timeseriesLatestDao.findByDeviceIdAndKey(deviceId, key));
            tsKvLatests.add(tsKvLatest);
        });
        return tsKvLatests;
    }


    private void saveTimeseries(Integer deviceId, TsKvEntry tsKvEntry) {
        TsKv tsKv = new TsKv();
        tsKv.setDeviceId(deviceId);
        tsKv.setTs(tsKvEntry.getTs());
        tsKv.setKey(tsKvEntry.getKey());
        tsKv.setStrValue(tsKvEntry.getStrValue().orElse(null));
        tsKv.setDoubleValue(tsKvEntry.getDoubleValue().orElse(null));
        tsKv.setLongValue(tsKvEntry.getLongValue().orElse(null));
        tsKv.setBooleanValue(tsKvEntry.getBooleanValue().orElse(null));
        tsKv.setJsonValue(tsKvEntry.getJsonValue().orElse(null));
        timeseriesDao.save(TsKvMapper.INSTANCE.TsKvToEntity(tsKv));
    }

    private void saveLatest(Integer deviceId, TsKvEntry tsKvEntry) {
        TsKvLatest latest = new TsKvLatest();
        latest.setDeviceId(deviceId);
        latest.setTs(tsKvEntry.getTs());
        latest.setKey(tsKvEntry.getKey());
        latest.setStrValue(tsKvEntry.getStrValue().orElse(null));
        latest.setDoubleValue(tsKvEntry.getDoubleValue().orElse(null));
        latest.setLongValue(tsKvEntry.getLongValue().orElse(null));
        latest.setBooleanValue(tsKvEntry.getBooleanValue().orElse(null));
        latest.setJsonValue(tsKvEntry.getJsonValue().orElse(null));
        TsKvLatest tsKvLatest = TsKvLatestMapper.INSTANCE.entityToTsKv(timeseriesLatestDao.findByDeviceIdAndKey(deviceId, tsKvEntry.getKey()));
        if (Objects.nonNull(tsKvLatest)) {
            latest.setId(tsKvLatest.getId());
        }
        timeseriesLatestDao.save(TsKvLatestMapper.INSTANCE.tsKvLatestToEntity(latest));
    }
}
