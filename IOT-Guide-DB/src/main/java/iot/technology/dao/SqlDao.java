package iot.technology.dao;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty(prefix = "database",value = "type",havingValue = "sql")
public @interface SqlDao {
}
