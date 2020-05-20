package iot.technology.dao;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan("iot.technology.dao.sql")
@EntityScan("com.sanshengshui.dao.model")
@EnableJpaRepositories("iot.technology.dao.sql")
@SqlDao
public class JpaDaoConfig {
}
