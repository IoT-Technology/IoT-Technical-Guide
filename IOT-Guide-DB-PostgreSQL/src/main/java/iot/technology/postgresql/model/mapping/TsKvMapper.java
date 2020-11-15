package iot.technology.postgresql.model.mapping;

import iot.technology.postgresql.dao.entity.TsKvEntity;
import iot.technology.postgresql.model.TsKv;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jamesmsw
 * @date 2020/11/15 8:38 下午
 */
@Mapper
public abstract class TsKvMapper {

    public final static TsKvMapper INSTANCE = Mappers.getMapper(TsKvMapper.class);

    public abstract TsKvEntity TsKvToEntity(TsKv tsKv);

    public abstract TsKv entityToTsKv(TsKvEntity tsKvEntity);
}
