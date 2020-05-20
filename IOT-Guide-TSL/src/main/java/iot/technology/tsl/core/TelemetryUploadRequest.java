package iot.technology.tsl.core;

import iot.technology.tsl.data.kv.KvEntry;
import iot.technology.tsl.session.FromDeviceRequestMsg;

import java.util.List;
import java.util.Map;

/**
 * @Author: 穆书伟
 * @Date: 19-4-2
 * @Version 1.0
 */
public interface TelemetryUploadRequest extends FromDeviceRequestMsg {

    Map<Long, List<KvEntry>> getData();
}
