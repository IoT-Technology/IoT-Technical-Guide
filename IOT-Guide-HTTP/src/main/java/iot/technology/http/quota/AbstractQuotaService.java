package iot.technology.http.quota;

import iot.technology.http.quota.inmemory.IntervalRegistryCleaner;
import iot.technology.http.quota.inmemory.IntervalRegistryLogger;
import iot.technology.http.quota.inmemory.KeyBasedIntervalRegistry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author james mu
 * @date 2019/8/12 上午9:16
 */
public class AbstractQuotaService implements QuotaService {

    private final KeyBasedIntervalRegistry requestRegistry;
    private final RequestLimitPolicy requestsPolicy;
    private final IntervalRegistryCleaner registryCleaner;
    private final IntervalRegistryLogger registryLogger;
    private final boolean enabled;

    public AbstractQuotaService(KeyBasedIntervalRegistry requestRegistry, RequestLimitPolicy requestsPolicy,
                                IntervalRegistryCleaner registryCleaner, IntervalRegistryLogger registryLogger,
                                boolean enabled) {
        this.requestRegistry = requestRegistry;
        this.requestsPolicy = requestsPolicy;
        this.registryCleaner = registryCleaner;
        this.registryLogger = registryLogger;
        this.enabled = enabled;
    }

    @PostConstruct
    public void init() {
        if (enabled) {
            registryCleaner.schedule();
            registryLogger.schedule();
        }
    }

    @PreDestroy
    public void close() {
        if (enabled) {
            registryCleaner.stop();
            registryLogger.stop();
        }
    }

    @Override
    public boolean isQuotaExceeded(String  key){
        if (enabled) {
            long count = requestRegistry.tick(key);
            return !requestsPolicy.isValid(count);
        }
        return false;
    }
}


