package iot.technology.postgresql.dao.entity;

import lombok.Data;

import javax.persistence.*;

import static iot.technology.postgresql.constants.ModelConstants.*;
import static iot.technology.postgresql.constants.ModelConstants.JSON_VALUE_COLUMN;

/**
 * @author jamesmsw
 * @description 设备最新键值表
 * @date 2020/11/15 3:34 下午
 */
@Data
@Entity
@Table(name = "ts_kv_latest")
public class TsKvLastestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_COLUMN)
    private Integer id;

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
