package iot.technology.jwt.refresh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author james mu
 * @date 2020/9/28 18:29
 */
@SpringBootApplication(scanBasePackages = "iot.technology.jwt.refresh")
public class JwtRefreshApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtRefreshApplication.class, args);
    }

}
