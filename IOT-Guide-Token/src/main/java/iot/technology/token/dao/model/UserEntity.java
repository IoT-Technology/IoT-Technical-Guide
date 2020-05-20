package iot.technology.token.dao.model;

import iot.technology.token.model.Authority;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: 穆书伟
 * @Date: 19-4-9 上午10:01
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 8250339805336035966L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Authority authority;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "tenant_id")
    private Long tenantId;

    @Column(name = "customer_id")
    private Long customerId;

    public UserEntity() {
        super();
    }


    public UserEntity(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.email = userEntity.getEmail();
        this.authority = userEntity.getAuthority();
        this.name = userEntity.getName();
        this.tenantId = userEntity.getTenantId();
        this.customerId = userEntity.getCustomerId();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserEntity [id=");
        builder.append(id);
        builder.append(", authority=");
        builder.append(authority);
        builder.append(", email=");
        builder.append(email);
        builder.append(", name=");
        builder.append(name);
        builder.append(", tenantId=");
        builder.append(tenantId);
        builder.append(", customerId=");
        builder.append(customerId);
        builder.append("]");
        return builder.toString();
    }


}
