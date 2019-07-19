package com.sanshengshui.token.dao.service.impl;

import com.sanshengshui.token.dao.model.CustomerEntity;
import com.sanshengshui.token.dao.service.CustomerService;
import com.sanshengshui.token.dao.sql.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerEntity findCustomerById(long customerId) {
        log.trace("Executing findCustomerById [{}]", customerId);
        return customerRepository.findById(customerId).get();
    }
}
