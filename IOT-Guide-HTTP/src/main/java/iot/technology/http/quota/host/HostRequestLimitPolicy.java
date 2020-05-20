package iot.technology.http.quota.host;

import iot.technology.http.quota.RequestLimitPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author james mu
 * @date 2019/8/12 上午10:14
 */
@Component
public class HostRequestLimitPolicy extends RequestLimitPolicy {

    public HostRequestLimitPolicy(@Value("${quota.host.limit}") long limit) {
        super(limit);
    }
}
