package iot.technology.jwt.mysql.model;

/**
 * @author james mu
 * @date 2020/9/9 23:01
 */
public class UserDTO {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
