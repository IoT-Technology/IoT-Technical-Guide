package iot.technology.http.quota.host;

import iot.technology.http.quota.inmemory.KeyBasedIntervalRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author james mu
 * @date 2019/8/12 上午9:56
 */
@Component
@Slf4j
public class HostRequestIntervalRegistry extends KeyBasedIntervalRegistry {

    public HostRequestIntervalRegistry(@Value("${quota.host.intervalMs}") long intervalDurationMs,
                                       @Value("${quota.host.ttlMs}") long ttlMs,
                                       @Value("${quota.host.whitelist}") String whiteList,
                                       @Value("${quota.host.blacklist}") String blackList) {
        super(intervalDurationMs, ttlMs, whiteList, blackList, "host");
    }
}
