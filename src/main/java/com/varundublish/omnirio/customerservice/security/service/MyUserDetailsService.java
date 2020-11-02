package com.varundublish.omnirio.customerservice.security.service;

import com.varundublish.omnirio.customerservice.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.varundublish.omnirio.customerservice.model.entities.User userFromDB = userRepository.findByUsername(username);

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userFromDB.getRole().getRoleCode()));
        User user = new User(userFromDB.getUsername(), userFromDB.getPassword(),authorities);
        return user;
    }
}