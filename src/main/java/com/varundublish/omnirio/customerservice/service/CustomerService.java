package com.varundublish.omnirio.customerservice.service;

import com.varundublish.omnirio.customerservice.model.entities.User;

public interface CustomerService {
    User getCustomerByCustomerId(String customerId);
    User getCustomerByUsername(String username);
}
