package iot.technology.token.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * @Author: 穆书伟
 * @Date: 19-4-11 下午2:36
 * @Version 1.0
 */
public class TokenErrorResponse {

    // HTTP Response Status Code
    private final HttpStatus status;

    // General Error message
    private final String message;

    // Error code
    private final TokenErrorCode errorCode;

    private final Date timestamp;

    protected TokenErrorResponse(final String message, final TokenErrorCode errorCode, HttpStatus status) {
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
        this.timestamp = new java.util.Date();
    }

    public static TokenErrorResponse of(final String message, final TokenErrorCode errorCode, HttpStatus status) {
        return new TokenErrorResponse(message, errorCode, status);
    }

    public Integer getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }

    public TokenErrorCode getErrorCode() {
        return errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
