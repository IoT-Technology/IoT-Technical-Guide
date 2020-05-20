package iot.technology.dao.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = ModelConstants.TENANT_PG_HIBERNATE_COLUMN_FAMILY_NAME)
public class TenantEntity {

    @Column(name = ModelConstants.ID_PROPERTY)
    private Integer id;

    @Column(name = ModelConstants.TENANT_EMAIL_PROPERTY)
    private String address;

    @Column(name = ModelConstants.TENANT_EMAIL_PROPERTY)
    private String email;

    @Column(name = ModelConstants.TENANT_PHONE_PROPERTY)
    private String phone;

    @Column(name = ModelConstants.TENANT_STATE_PROPERTY)
    private String state;

    @Column(name = ModelConstants.TENANT_ZIP_PROPERTY)
    private String zip;
}
