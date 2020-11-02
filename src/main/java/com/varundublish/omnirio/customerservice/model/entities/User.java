package com.varundublish.omnirio.customerservice.model.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "USER_ID", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column(unique = true)
    private String username;
    private String password;

    private String firstName;
    private String lastName;

    private LocalDate dateOfBirth;

    private Character gender;

    private String phoneNumber;

    @OneToOne
    @JoinColumn(name="ROLE_ID")
    private Role role;

    private boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId) &&
                username.equals(user.username) &&
                dateOfBirth.equals(user.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, dateOfBirth);
    }
}
