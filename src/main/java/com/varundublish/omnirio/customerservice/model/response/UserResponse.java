package com.varundublish.omnirio.customerservice.model.response;

import com.varundublish.omnirio.customerservice.model.entities.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserResponse {

    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Character gender;
    private String phoneNumber;
    private String role;


    public void setRole(Role role){
        this.role = role.getRoleCode().toString();
    }

}
