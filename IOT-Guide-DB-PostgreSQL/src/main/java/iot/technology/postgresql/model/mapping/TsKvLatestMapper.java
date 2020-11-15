package iot.technology.postgresql.model.mapping;

import iot.technology.postgresql.dao.entity.TsKvLastestEntity;
import iot.technology.postgresql.model.TsKvLatest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jamesmsw
 * @date 2020/11/15 8:38 下午
 */
@Mapper
public abstract class TsKvLatestMapper {

    public final static TsKvLatestMapper INSTANCE = Mappers.getMapper(TsKvLatestMapper.class);

    public abstract TsKvLastestEntity tsKvLatestToEntity(TsKvLatest tsKvLatest);

    public abstract TsKvLatest entityToTsKv(TsKvLastestEntity tsKvLastestEntity);
}
