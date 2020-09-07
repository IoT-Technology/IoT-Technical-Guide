package iot.technology.jwt.without;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author james mu
 * @date 2020/9/7 17:59
 */
@SpringBootApplication(scanBasePackages = {"iot.technology.jwt.without"})
public class JwtWithoutJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtWithoutJpaApplication.class, args);
    }
}
