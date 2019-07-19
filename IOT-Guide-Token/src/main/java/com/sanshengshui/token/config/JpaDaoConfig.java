package com.sanshengshui.token.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.sanshengshui.token.dao")
@EntityScan("com.sanshengshui.token.dao.model")
@EnableJpaRepositories("com.sanshengshui.token.dao.sql")
@EnableTransactionManagement
public class JpaDaoConfig {
}
