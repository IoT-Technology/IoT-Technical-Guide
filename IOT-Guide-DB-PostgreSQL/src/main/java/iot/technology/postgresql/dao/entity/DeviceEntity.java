package iot.technology.postgresql.dao.entity;


import javax.persistence.*;

import static iot.technology.postgresql.constants.ModelConstants.*;

/**
 * @author jamesmsw
 * @description 设备表
 * @date 2020/11/15 3:58 下午
 */
@Entity
@Table(name = "device")
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID_COLUMN)
    private Integer id;

    @Column(name = NAME_COLUMN)
    private String name;

    @Column(name = ADDITIONAL_INFO_COLUMN)
    private String additionalInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
