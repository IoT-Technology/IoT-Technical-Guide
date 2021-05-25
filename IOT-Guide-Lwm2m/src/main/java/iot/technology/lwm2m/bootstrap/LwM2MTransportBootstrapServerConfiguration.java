package iot.technology.lwm2m.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author mushuwei
 */
@Slf4j
@Component
public class LwM2MTransportBootstrapServerConfiguration {

    @PostConstruct
    public void init() {

    }

    @PreDestroy
    public void shutdown() {

    }
}
