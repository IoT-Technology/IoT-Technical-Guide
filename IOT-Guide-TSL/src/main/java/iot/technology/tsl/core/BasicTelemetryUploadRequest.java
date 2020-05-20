package iot.technology.tsl.core;

import iot.technology.tsl.data.kv.KvEntry;
import iot.technology.tsl.session.SessionMsgType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 穆书伟
 * @Date: 19-4-2
 * @Version 1.0
 */
public class BasicTelemetryUploadRequest extends BasicRequest implements TelemetryUploadRequest {

    private static final long serialVersionUID = 1L;

    private final Map<Long, List<KvEntry>> data;

    public BasicTelemetryUploadRequest() {
        this(DEFAULT_REQUEST_ID);
    }

    public BasicTelemetryUploadRequest(Integer requestId) {
        super(requestId);
        this.data = new HashMap<>();
    }

    public void add(long ts, KvEntry entry) {
        List<KvEntry> tsEntries = data.get(ts);
        if (tsEntries == null) {
            tsEntries = new ArrayList<>();
            data.put(ts, tsEntries);
        }
        tsEntries.add(entry);
    }

    @Override
    public SessionMsgType getMsgType() {
        return SessionMsgType.POST_TELEMETRY_REQUEST;
    }

    @Override
    public Map<Long, List<KvEntry>> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "BasicTelemetryUploadRequest [dao=" + data + "]";
    }


}
