package iot.technology.postgresql.dao;

import iot.technology.postgresql.dao.entity.DeviceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jamesmsw
 * @date 2020/11/15 6:07 下午
 */
@Repository("deviceDao")
public interface DeviceDao extends CrudRepository<DeviceEntity, Integer> {
}
