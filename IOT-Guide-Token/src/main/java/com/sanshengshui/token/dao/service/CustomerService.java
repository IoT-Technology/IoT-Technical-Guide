package com.sanshengshui.token.dao.service;

import com.sanshengshui.token.dao.model.CustomerEntity;

public interface CustomerService {

    CustomerEntity findCustomerById(long customerId);

}
