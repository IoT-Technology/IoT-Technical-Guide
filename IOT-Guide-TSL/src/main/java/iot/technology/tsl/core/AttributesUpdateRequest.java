package iot.technology.tsl.core;

import iot.technology.tsl.data.kv.AttributeKvEntry;
import iot.technology.tsl.session.FromDeviceRequestMsg;

import java.util.Set;

/**
 * @Author: 穆书伟
 * @Date: 19-4-2
 * @Version 1.0
 */
public interface AttributesUpdateRequest extends FromDeviceRequestMsg {

    Set<AttributeKvEntry> getAttributes();

}
