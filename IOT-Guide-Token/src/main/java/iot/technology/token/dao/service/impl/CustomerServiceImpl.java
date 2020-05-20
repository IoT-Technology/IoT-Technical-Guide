package iot.technology.token.dao.service.impl;

import iot.technology.token.dao.model.CustomerEntity;
import iot.technology.token.dao.service.CustomerService;
import iot.technology.token.dao.sql.CustomerRepository;
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
