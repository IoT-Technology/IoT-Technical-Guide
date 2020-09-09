package iot.technology.jwt.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author james mu
 * @date 2020/9/9 22:07
 */
@SpringBootApplication(scanBasePackages = "iot.technology.jwt.mysql")
public class JwtJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtJpaApplication.class, args);
    }
}
