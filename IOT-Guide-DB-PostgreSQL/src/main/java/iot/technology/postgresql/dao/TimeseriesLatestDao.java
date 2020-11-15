package iot.technology.postgresql.dao;

import iot.technology.postgresql.dao.entity.TsKvLastestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jamesmsw
 * @date 2020/11/15 2:41 下午
 */
@Repository("timeseriesLatestDao")
public interface TimeseriesLatestDao extends CrudRepository<TsKvLastestEntity, Integer> {
}
