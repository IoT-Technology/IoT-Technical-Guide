package iot.technology.token.dao.service;

import iot.technology.token.dao.model.CustomerEntity;

public interface CustomerService {

    CustomerEntity findCustomerById(long customerId);

}
