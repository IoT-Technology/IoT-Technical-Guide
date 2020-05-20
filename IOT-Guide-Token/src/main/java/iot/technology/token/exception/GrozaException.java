package iot.technology.token.exception;

/**
 * @author james mu
 * @date 19-1-24 下午4:44
 */
public class GrozaException extends Exception{

    private static final long serialVersionUID = 1L;

    private GrozaErrorCode errorCode;

    public GrozaException() {
        super();
    }

    public GrozaException(GrozaErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public GrozaException(String message, GrozaErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public GrozaException(String message, Throwable cause, GrozaErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public GrozaException(Throwable cause, GrozaErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public GrozaErrorCode getErrorCode() {
        return errorCode;
    }

}
