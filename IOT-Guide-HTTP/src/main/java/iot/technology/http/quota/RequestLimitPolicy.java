package iot.technology.http.quota;

/**
 * @author james mu
 * @date 2019/8/12 上午9:18
 */
public abstract class RequestLimitPolicy {

    private final long limit;

    public RequestLimitPolicy(long limit) {
        this.limit = limit;
    }

    public boolean isValid(long currentValue) {
        return currentValue <= limit;
    }
}
