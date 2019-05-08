package com.sanshengshui.dao;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan("com.sanshengshui.dao.sql")
@EntityScan("com.sanshengshui.dao.model")
@EnableJpaRepositories("com.sanshengshui.dao.sql")
@SqlDao
public class JpaDaoConfig {
}
