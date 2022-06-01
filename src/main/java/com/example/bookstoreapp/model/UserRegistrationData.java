package com.example.bookstoreapp.model;

import lombok.Data;

import javax.persistence.*;
import com.example.bookstoreapp.controller.dto.UserRegistrationDTO;

@Entity
@Table(name = "user_registration_table")
public @Data
class UserRegistrationData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    public UserRegistrationData() {
    }

    public UserRegistrationData(UserRegistrationDTO userRegistrationDTO) {
        this.updateUserRegistrationData(userRegistrationDTO);
    }

    public void updateUserRegistrationData(UserRegistrationDTO userRegistrationDTO) {
        this.firstName = userRegistrationDTO.firstName;
        this.lastName = userRegistrationDTO.lastName;
        this.email = userRegistrationDTO.email;
        this.password = userRegistrationDTO.password;
        this.address = userRegistrationDTO.address;
    }
}
