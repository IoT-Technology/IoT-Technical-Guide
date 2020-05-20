package iot.technology.multitenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"iot.technology.multitenant"})
public class MultiTenantServer {
    public static void main(String[] args) {
        SpringApplication.run(MultiTenantServer.class);
    }
}
