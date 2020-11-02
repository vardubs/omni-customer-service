package com.varundublish.omnirio.customerservice.service;

import com.varundublish.omnirio.customerservice.exceptions.CustomerNotFoundException;
import com.varundublish.omnirio.customerservice.model.entities.User;
import com.varundublish.omnirio.customerservice.model.request.UserRequest;
import com.varundublish.omnirio.customerservice.model.response.UserResponse;
import com.varundublish.omnirio.customerservice.repository.RoleRepository;
import com.varundublish.omnirio.customerservice.repository.UserRepository;
import com.varundublish.omnirio.customerservice.util.UserUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BranchManagerServiceServiceImpl implements BranchManagerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;




    //Create new User
    @Override
    public User createUser(UserRequest userRequest){

        User user = modelMapper.map(userRequest, User.class);

        //Create a new User Id
        user.setUserId(UserUtility.generateUserId());
        //Encrypt Password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //Update Role
        user.setRole(roleRepository.findByRoleCode(userRequest.getRole()));
        return userRepository.save(user);
    }

    //Update User
    public User updateUser(UserRequest userRequest, String userId){
        Optional<User> existingUserOptional = userRepository.findByUserId(userId);

        if (!existingUserOptional.isPresent())
            throw new CustomerNotFoundException(userId);

        //Only Phone Number and Role Can be updated
        User existingUser = existingUserOptional.get();
        existingUser.setPhoneNumber(userRequest.getPhoneNumber());
        existingUser.setRole(roleRepository.findByRoleCode(userRequest.getRole()));

        return userRepository.save(existingUser);
    }

    //Get User
    public User getUser(String userId){
        Optional<User> user = userRepository.findByUserId(userId);

        if (!user.isPresent())
            throw new CustomerNotFoundException(userId);

        return user.get();
    }

    //Get All Users
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(s-> modelMapper.map(s, UserResponse.class))
                .collect(Collectors.toList());
    }

    //Deactivate User, Setting the active flag to false
    public User deactivateUser(String userId){
        Optional<User> userOptional = userRepository.findByUserId(userId);

        if (!userOptional.isPresent())
            throw new CustomerNotFoundException(userId);

        User user = userOptional.get();
        user.setActive(Boolean.FALSE);
        userRepository.save(user);
        return user;
    }


  //Todo : Similar Crud APIs for Roles if Required


}
