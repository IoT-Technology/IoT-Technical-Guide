package com.sanshengshui.token.dao.sql;

import com.sanshengshui.token.dao.model.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
}
