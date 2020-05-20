package iot.technology.dao.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = ModelConstants.DEVICE_PG_HIBERNATE_COLUMN_FAMILY_NAME)
public class DeviceEntity {

    @Column(name = ModelConstants.ID_PROPERTY)
    private Integer id;

    @Column(name = ModelConstants.DEVICE_CUSTOMER_ID_PROPERTY)
    private Integer customerId;

    @Column(name = ModelConstants.DEVICE_NAME_PROPERTY)
    private String name;

    @Column(name = ModelConstants.DEVICE_SEARCH_TEXT_PROPERTY)
    private String searchText;

    @Column(name = ModelConstants.DEVICE_TENANT_ID_PROPERTY)
    private Integer tenantId;
}
