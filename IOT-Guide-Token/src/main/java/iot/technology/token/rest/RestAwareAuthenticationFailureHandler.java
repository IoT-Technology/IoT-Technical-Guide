package iot.technology.token.rest;

import iot.technology.token.exception.TokenErrorResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 穆书伟
 * @Date: 19-4-11 下午2:32
 * @Version 1.0
 */
@Component
public class RestAwareAuthenticationFailureHandler implements AuthenticationFailureHandler  {

    private final TokenErrorResponseHandler errorResponseHandler;

    @Autowired
    public RestAwareAuthenticationFailureHandler(TokenErrorResponseHandler errorResponseHandler) {
        this.errorResponseHandler = errorResponseHandler;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        errorResponseHandler.handle(e, response);
    }
}
