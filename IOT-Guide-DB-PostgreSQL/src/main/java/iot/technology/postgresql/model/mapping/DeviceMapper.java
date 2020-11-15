package iot.technology.postgresql.model.mapping;

import iot.technology.postgresql.dao.entity.DeviceEntity;
import iot.technology.postgresql.model.Device;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jamesmsw
 * @date 2020/11/15 8:05 下午
 */
@Mapper
public abstract class DeviceMapper {

    public final static DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    public abstract DeviceEntity deviceToEntity(Device device);

    public abstract Device entityToDevice(DeviceEntity entity);
}
