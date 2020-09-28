package iot.technology.jwt.refresh.dao;

/**
 * @author james mu
 * @date 2020/9/28 18:27
 */
public class AuthenticationResponse {

    private String token;

    public AuthenticationResponse()
    {

    }

    public AuthenticationResponse(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
