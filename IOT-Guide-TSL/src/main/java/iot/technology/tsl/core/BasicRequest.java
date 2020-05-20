package iot.technology.tsl.core;


/**
 * @Author: 穆书伟
 * @Date: 19-4-2
 * @Version 1.0
 */
public class BasicRequest {

    public static final Integer DEFAULT_REQUEST_ID = 0;

    private final Integer requestId;

    public BasicRequest(Integer requestId){
        this.requestId = requestId;
    }

    public Integer getRequestId() {
        return requestId;
    }
}
