package iot.technology.token.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * @Author: 穆书伟
 * @Date: 19-4-10 上午11:06
 * @Version 1.0
 */
public class AuthMethodNotSupportedException extends AuthenticationServiceException {

    private static final long serialVersionUID = 3705043083010304496L;

    public AuthMethodNotSupportedException(String msg) {
        super(msg);
    }
}
