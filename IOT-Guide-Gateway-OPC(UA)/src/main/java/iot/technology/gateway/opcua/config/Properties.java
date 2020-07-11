package iot.technology.gateway.opcua.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author james mu
 * @date 2020/7/9 23:59
 */
@Getter
@Configuration
@PropertySource("classpath:opcua.properties")
public class Properties {
    @Value("${opcua.server.endpoint.url}")
    private String endpointUrl;
    @Value("${opcua.client.app.name}")
    private String appName;
}
