package iot.technology.token.exception;

import iot.technology.token.model.token.JwtToken;
import org.springframework.security.core.AuthenticationException;


/**
 * @Author: 穆书伟
 * @Date: 19-4-9 上午9:44
 * @Version 1.0
 */
public class JwtExpiredTokenException extends AuthenticationException {

    private static final long serialVersionUID = -5959543783324224864L;

    private JwtToken token;

    public JwtExpiredTokenException(String msg) {
        super(msg);
    }

    public JwtExpiredTokenException(JwtToken token, String msg, Throwable t) {
        super(msg, t);
        this.token = token;
    }

    public String token() {
        return this.token.getToken();
    }
}
