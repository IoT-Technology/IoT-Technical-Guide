package iot.technology.token.exception;

/**
 * @Author: 穆书伟
 * @Date: 19-4-11 下午2:41
 * @Version 1.0
 */
public class TokenException extends Exception {

    private static final long serialVersionUID = 1L;

    private TokenErrorCode errorCode;

    public TokenException() {
        super();
    }

    public TokenException(TokenErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public TokenException(String message, TokenErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public TokenException(String message, Throwable cause, TokenErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public TokenException(Throwable cause, TokenErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public TokenErrorCode getErrorCode() {
        return errorCode;
    }
}
