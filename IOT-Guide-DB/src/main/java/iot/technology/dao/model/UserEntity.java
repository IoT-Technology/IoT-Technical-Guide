package iot.technology.dao.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = ModelConstants.USER_PG_HIBERNATE_COLUMN_FAMILY_NAME)
public class UserEntity {

    @Column(name = ModelConstants.ID_PROPERTY)
    private Integer id;

    @Column(name = ModelConstants.USER_CUSTOMER_ID_PROPERTY)
    private Integer customerId;

    @Column(name = ModelConstants.USER_EMAIL_PROPERTY)
    private String email;

    @Column(name = ModelConstants.USER_FIRST_NAME_PROPERTY)
    private String firstName;

    @Column(name = ModelConstants.USER_LAST_NAME_PROPERTY)
    private String lastName;

    @Column(name = ModelConstants.USER_TENANT_ID_PROPERTY)
    private Integer tenantId;
}
