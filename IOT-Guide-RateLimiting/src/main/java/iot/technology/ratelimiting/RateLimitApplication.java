package iot.technology.ratelimiting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mushuwei
 */
@SpringBootApplication(scanBasePackages = {"iot.technology.ratelimiting"})
public class RateLimitApplication {

    public static void main(String[] args) {
        SpringApplication.run(RateLimitApplication.class);
    }
}
