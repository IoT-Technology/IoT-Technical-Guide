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
    @Value("${opcua.server.idp.username}")
    private String idpUsername;
    @Value("${opcua.server.idp.password}")
    private String idpPassword;
    @Value("${opcua.client.app.name}")
    private String appName;
    @Value("${opcua.client.app.uri}")
    private String appUri;
    @Value("${opcua.client.cert.path}")
    private String certPath;
    @Value("${opcua.client.cert.file}")
    private String certFile;
    @Value("${opcua.client.cert.alias}")
    private String certAlias;
    @Value("${opcua.client.cert.common.name}")
    private String commonName;
    @Value("${opcua.client.cert.organization}")
    private String organization;
    @Value("${opcua.client.cert.organization.unit}")
    private String orgUnit;
    @Value("${opcua.client.cert.locality.name}")
    private String localityName;
    @Value("${opcua.client.cert.state.name}")
    private String stateName;
    @Value("${opcua.client.cert.country.code}")
    private String countryCode;
    @Value("${opcua.client.cert.dns.name}")
    private String dnsName;
    @Value("${opcua.client.cert.ip.address}")
    private String ipAddress;
    @Value("${opcua.client.cert.keystore.password}")
    private String keyPassword;
}
