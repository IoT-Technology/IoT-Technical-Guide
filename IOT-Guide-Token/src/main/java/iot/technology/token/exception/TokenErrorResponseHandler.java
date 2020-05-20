package iot.technology.token.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 穆书伟
 * @Date: 19-4-11 下午2:34
 * @Version 1.0
 */
@Component
public class TokenErrorResponseHandler implements AccessDeniedHandler  {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException,
            ServletException {
        if (!response.isCommitted()) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            mapper.writeValue(response.getWriter(),
                    TokenErrorResponse.of("You don't have permission to perform this operation!",
                            TokenErrorCode.PERMISSION_DENIED, HttpStatus.FORBIDDEN));
        }
    }

    public void handle(Exception exception, HttpServletResponse response) {
        if (!response.isCommitted()) {
            try {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                if (exception instanceof TokenException) {
                    handleTokenException((TokenException) exception, response);
                } else if (exception instanceof AccessDeniedException) {
                    handleAccessDeniedException(response);
                } else if (exception instanceof AuthenticationException) {
                    handleAuthenticationException((AuthenticationException) exception, response);
                } else {
                    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    mapper.writeValue(response.getWriter(), TokenErrorResponse.of(exception.getMessage(),
                            TokenErrorCode.GENERAL, HttpStatus.INTERNAL_SERVER_ERROR));
                }
            } catch (IOException e) {
            }
        }
    }

    private void handleTokenException(TokenException TokenException, HttpServletResponse response) throws IOException {

        TokenErrorCode errorCode = TokenException.getErrorCode();
        HttpStatus status;

        switch (errorCode) {
            case AUTHENTICATION:
                status = HttpStatus.UNAUTHORIZED;
                break;
            case PERMISSION_DENIED:
                status = HttpStatus.FORBIDDEN;
                break;
            case INVALID_ARGUMENTS:
                status = HttpStatus.BAD_REQUEST;
                break;
            case ITEM_NOT_FOUND:
                status = HttpStatus.NOT_FOUND;
                break;
            case BAD_REQUEST_PARAMS:
                status = HttpStatus.BAD_REQUEST;
                break;
            case GENERAL:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
        }

        response.setStatus(status.value());
        mapper.writeValue(response.getWriter(), TokenErrorResponse.of(TokenException.getMessage(), errorCode, status));
    }

    private void handleAccessDeniedException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        mapper.writeValue(response.getWriter(),
                TokenErrorResponse.of("You don't have permission to perform this operation!",
                        TokenErrorCode.PERMISSION_DENIED, HttpStatus.FORBIDDEN));

    }

    private void handleAuthenticationException(AuthenticationException authenticationException, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        if (authenticationException instanceof BadCredentialsException) {
            mapper.writeValue(response.getWriter(), TokenErrorResponse.of("Invalid username or password", TokenErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
        } else if (authenticationException instanceof JwtExpiredTokenException) {
            mapper.writeValue(response.getWriter(), TokenErrorResponse.of("Token has expired", TokenErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
        } else if (authenticationException instanceof AuthMethodNotSupportedException) {
            mapper.writeValue(response.getWriter(), TokenErrorResponse.of(authenticationException.getMessage(), TokenErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
        }
        mapper.writeValue(response.getWriter(), TokenErrorResponse.of("Authentication failed", TokenErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
    }
}
