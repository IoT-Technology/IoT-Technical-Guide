package iot.technology.token.exception;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Author: 穆书伟
 * @Date: 19-4-11 下午2:37
 * @Version 1.0
 */
public enum  TokenErrorCode {

    GENERAL(2),
    AUTHENTICATION(10),
    JWT_TOKEN_EXPIRED(11),
    PERMISSION_DENIED(20),
    INVALID_ARGUMENTS(30),
    BAD_REQUEST_PARAMS(31),
    ITEM_NOT_FOUND(32);

    private int errorCode;

    TokenErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }

}
