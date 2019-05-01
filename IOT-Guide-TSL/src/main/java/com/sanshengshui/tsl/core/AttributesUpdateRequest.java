package com.sanshengshui.tsl.core;

import com.sanshengshui.tsl.data.kv.AttributeKvEntry;
import com.sanshengshui.tsl.session.FromDeviceRequestMsg;

import java.util.Set;

/**
 * @Author: 穆书伟
 * @Date: 19-4-2
 * @Version 1.0
 */
public interface AttributesUpdateRequest extends FromDeviceRequestMsg {

    Set<AttributeKvEntry> getAttributes();

}
