package iot.technology.http.quota.host;

import iot.technology.http.quota.AbstractQuotaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author james mu
 * @date 2019/8/12 上午9:13
 */
@Service
@Slf4j
public class HostRequestsQuotaService extends AbstractQuotaService {

    public HostRequestsQuotaService(HostRequestIntervalRegistry requestRegistry, HostRequestLimitPolicy requestsPolicy,
                                    HostIntervalRegistryCleaner registryCleaner, HostIntervalRegistryLogger registryLogger,
                                    @Value("${quota.host.enabled}") boolean enabled) {
        super(requestRegistry, requestsPolicy, registryCleaner, registryLogger, enabled);
    }
}
