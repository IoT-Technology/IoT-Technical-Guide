package iot.technology.postgresql.service;

import iot.technology.postgresql.model.Device;

/**
 * @author jamesmsw
 * @date 2020/11/15 6:19 下午
 */
public interface DeviceService {

    Device saveDevice(Device device);
}
