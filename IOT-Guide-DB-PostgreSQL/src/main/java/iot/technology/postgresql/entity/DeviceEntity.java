package iot.technology.postgresql.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static iot.technology.postgresql.constants.ModelConstants.*;

/**
 * @author jamesmsw
 * @description 设备表
 * @date 2020/11/15 3:58 下午
 */
@Data
@Entity
@Table(name = "device")
public class DeviceEntity {

    @Id
    @Column(name = ID_COLUMN)
    private Integer id;

    @Column(name = NAME_COLUMN)
    private String name;

    @Column(name = ADDITIONAL_INFO_COLUMN)
    private String additionalInfo;

}
