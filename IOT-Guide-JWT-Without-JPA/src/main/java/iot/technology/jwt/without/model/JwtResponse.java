package iot.technology.jwt.without.model;


import java.io.Serializable;

/**
 * @author james mu
 * @date 2020/9/7 19:11
 */
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
