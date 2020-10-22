package iot.technology.oauth2.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author james mu
 * @date 2020/10/21 14:16
 */
@SpringBootApplication(scanBasePackages = "iot.technology.oauth2.authorization")
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class);
    }
}
