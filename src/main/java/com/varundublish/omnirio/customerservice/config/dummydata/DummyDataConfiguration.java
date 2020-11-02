package com.varundublish.omnirio.customerservice.config.dummydata;

import com.varundublish.omnirio.customerservice.model.entities.Role;
import com.varundublish.omnirio.customerservice.model.entities.User;
import com.varundublish.omnirio.customerservice.repository.RoleRepository;
import com.varundublish.omnirio.customerservice.repository.UserRepository;
import com.varundublish.omnirio.customerservice.util.UserUtility;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Log
@Configuration
public class DummyDataConfiguration {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Sample Dummy Data in DB for Testing
     */
    @PostConstruct
    public void insertDummyData(){

        Role roleCustomer = new Role(1L, "Customer" ,"CUSTOMER");
        Role roleBranchManager = new Role(2L, "Branch Manager", "BRANCH-MANAGER");
        roleRepository.save(roleCustomer);
        roleRepository.save(roleBranchManager);

        User userCustomer = new User();
        userCustomer.setUserId(UserUtility.generateUserId());
        userCustomer.setFirstName("Varun");
        userCustomer.setLastName("Dublish");
        userCustomer.setUsername("foo");
        userCustomer.setPassword(passwordEncoder.encode("foo"));
        userCustomer.setDateOfBirth(LocalDate.parse("1985-11-15"));
        userCustomer.setGender('M');
        userCustomer.setPhoneNumber("12345");
        userCustomer.setRole(roleCustomer);
        userCustomer.setActive(Boolean.TRUE);

        userRepository.save(userCustomer);
        log.info("Customer Saved");

        User userBranchManager = new User();
        userBranchManager.setUserId(UserUtility.generateUserId());
        userBranchManager.setUsername("admin");
        userBranchManager.setFirstName("Branch");
        userBranchManager.setLastName("Manager");
        userBranchManager.setPassword(passwordEncoder.encode("admin"));
        userBranchManager.setDateOfBirth(LocalDate.parse("1970-11-15"));
        userBranchManager.setGender('M');
        userBranchManager.setPhoneNumber("45678");
        userBranchManager.setRole(roleBranchManager);
        userBranchManager.setActive(Boolean.TRUE);

        userRepository.save(userBranchManager);
        log.info("Branch Manager Saved");
    }



}
