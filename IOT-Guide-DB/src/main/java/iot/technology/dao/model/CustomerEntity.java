package iot.technology.dao.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = ModelConstants.CUSTOMER_PG_HIBERNATE_COLUMN_FAMILY_NAME)
public class CustomerEntity {

    @Column(name = ModelConstants.ID_PROPERTY)
    private Integer id;

    @Column(name = ModelConstants.CUSTOMER_ADDRESS_PROPERTY)
    private String address;

    @Column(name = ModelConstants.CUSTOMER_EMAIL_PROPERTY)
    private String email;

    @Column(name = ModelConstants.CUSTOMER_PHONE_PROPERTY)
    private String phone;

    @Column(name = ModelConstants.CUSTOMER_STATE_PROPERTY)
    private String state;

    @Column(name = ModelConstants.CUSTOMER_ZIP_PROPERTY)
    private String zip;
}
