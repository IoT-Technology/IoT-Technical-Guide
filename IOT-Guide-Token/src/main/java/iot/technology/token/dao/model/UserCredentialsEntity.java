package iot.technology.token.dao.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user_credentials")
public class UserCredentialsEntity implements Serializable {


    public static final long serialVersionUID = -2108436378880529163L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "password")
    private String password;

    @Column(name = "activate_token")
    private String activateToken;

    @Column(name = "reset_token")
    private String resetToken;

    public UserCredentialsEntity() {
        super();
    }



}
