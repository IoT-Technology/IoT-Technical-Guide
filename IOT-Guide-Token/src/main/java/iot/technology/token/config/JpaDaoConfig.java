package iot.technology.token.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan("iot.technology.token.dao")
@EntityScan("iot.technology.token.dao.model")
@EnableJpaRepositories("iot.technology.token.dao.sql")
@EnableTransactionManagement
public class JpaDaoConfig {
}
