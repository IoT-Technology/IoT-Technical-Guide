package iot.technology.token.exception;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author james mu
 * @date 19-1-24 下午4:43
 */
public enum GrozaErrorCode {

    GENERAL(2),
    AUTHENTICATION(10),
    JWT_TOKEN_EXPIRED(11),
    PERMISSION_DENIED(20),
    INVALID_ARGUMENTS(30),
    BAD_REQUEST_PARAMS(31),
    ITEM_NOT_FOUND(32);

    private int errorCode;

    GrozaErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }

}
