package com.example.bookstoreapp.service;

import com.example.bookstoreapp.model.UserRegistrationData;

import java.util.List;

public interface IUserRegistrationService {
    List<UserRegistrationData> getUserRegistrationData();

    UserRegistrationData getUserRegistrationDataByUserId(int userId);

    UserRegistrationData createUserRegistrationData(com.example.bookstoreapp.controller.dto.UserRegistrationDTO userRegistrationDTO);

    UserRegistrationData updateUserRegistrationData(int userId, com.example.bookstoreapp.controller.dto.UserRegistrationDTO userRegistrationDTO);

    UserRegistrationData getUserByEmailId(String email);
}
