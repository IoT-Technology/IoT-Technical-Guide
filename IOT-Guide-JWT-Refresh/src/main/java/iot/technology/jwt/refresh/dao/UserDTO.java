package iot.technology.jwt.refresh.dao;

import lombok.Data;
import lombok.ToString;

/**
 * @author james mu
 * @date 2020/9/28 18:24
 */
@Data
@ToString
public class UserDTO {

    private String username;
    private String password;
    private String role;

}
