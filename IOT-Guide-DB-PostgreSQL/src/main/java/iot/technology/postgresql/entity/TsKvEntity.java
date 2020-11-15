package iot.technology.postgresql.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static iot.technology.postgresql.constants.ModelConstants.*;

/**
 * @author jamesmsw
 * @description 设备历史键值表
 * @date 2020/11/15 3:30 下午
 */
@Data
@Entity
@Table(name = "ts_kv")
public class TsKvEntity {

    @Column(name = DEVICE_ID_COLUMN)
    private Integer deviceId;

    @Column(name = KEY_COLUMN)
    private String key;

    @Column(name = TS_COLUMN)
    private Long ts;

    @Column(name = BOOLEAN_VALUE_COLUMN)
    private Boolean booleanValue;

    @Column(name = STRING_VALUE_COLUMN)
    private String strValue;

    @Column(name = LONG_VALUE_COLUMN)
    private Long longValue;

    @Column(name = DOUBLE_VALUE_COLUMN)
    private Double doubleValue;

    @Column(name = JSON_VALUE_COLUMN)
    private String jsonValue;

}
