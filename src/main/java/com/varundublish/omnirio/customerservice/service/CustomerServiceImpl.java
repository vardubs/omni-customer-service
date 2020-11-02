package com.varundublish.omnirio.customerservice.service;

import com.varundublish.omnirio.customerservice.model.entities.User;
import com.varundublish.omnirio.customerservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getCustomerByCustomerId(String customerId) {
        return userRepository.findByUserId(customerId).orElseThrow(RuntimeException::new);

    }

    @Override
    public User getCustomerByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    /**
     * Checks if the given email already exists in the database repository or not
     *
     * @return true if the email exists else false
     */
    /*public Boolean emailAlreadyExists(String email) {
        return userService.existsByEmail(email);
    }*/

    /**
     * Checks if the given email already exists in the database repository or not
     *
     * @return true if the email exists else false
     */
    /*public Boolean usernameAlreadyExists(String username) {
        return userService.existsByUsername(username);
    }*/
}
