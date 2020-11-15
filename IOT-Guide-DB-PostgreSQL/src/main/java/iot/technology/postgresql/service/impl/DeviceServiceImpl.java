package iot.technology.postgresql.service.impl;

import iot.technology.postgresql.dao.DeviceDao;
import iot.technology.postgresql.dao.entity.DeviceEntity;
import iot.technology.postgresql.model.Device;
import iot.technology.postgresql.model.mapping.DeviceMapper;
import iot.technology.postgresql.service.DeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jamesmsw
 * @date 2020/11/15 7:43 下午
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {

    @Resource(name = "deviceDao")
    private DeviceDao deviceDao;

    @Override
    public Device saveDevice(Device device) {
        DeviceEntity deviceEntity = DeviceMapper.INSTANCE.deviceToEntity(device);
        DeviceEntity entity = deviceDao.save(deviceEntity);
        return DeviceMapper.INSTANCE.entityToDevice(entity);
    }
}
