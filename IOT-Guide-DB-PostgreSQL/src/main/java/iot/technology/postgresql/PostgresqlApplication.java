package iot.technology.postgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "iot.technology.postgresql")
public class PostgresqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostgresqlApplication.class);
    }
}
