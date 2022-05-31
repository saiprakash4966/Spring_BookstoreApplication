package com.example.bookstoreapp.service;

import com.example.bookstoreapp.exceptions.UserRegistrationCustomException;
import com.example.bookstoreapp.model.UserRegistrationData;
import com.example.bookstoreapp.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRegistrationService implements IUserRegistrationService{
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Override
    public List<UserRegistrationData> getUserRegistrationData() {
        return userRegistrationRepository.findAll();
    }

    @Override
    public UserRegistrationData getUserRegistrationDataByUserId(int userId) {
        return userRegistrationRepository.findById(userId)
                .orElseThrow(() -> new UserRegistrationCustomException("User with id "
                        + userId +
                        " not found"));
    }

    @Override
    public UserRegistrationData getUserByEmailId(String email) {
        UserRegistrationData userRegistrationData = userRegistrationRepository.getUserByEmailId(email);
        if (userRegistrationData != null)
            return userRegistrationData;
        else
            throw new UserRegistrationCustomException("User with email id " + email + " not found");
    }

    @Override
    public UserRegistrationData createUserRegistrationData(com.example.bookstoreapp.controller.dto.UserRegistrationDTO userRegistrationDTO) {
        UserRegistrationData userRegistrationData = new UserRegistrationData(userRegistrationDTO);
        return userRegistrationRepository.save(userRegistrationData);
    }

    @Override
    public UserRegistrationData updateUserRegistrationData(int userId, com.example.bookstoreapp.controller.dto.UserRegistrationDTO userRegistrationDTO) {
        UserRegistrationData userRegistrationData = this.getUserRegistrationDataByUserId(userId);
        userRegistrationData.updateUserRegistrationData(userRegistrationDTO);
        return userRegistrationRepository.save(userRegistrationData);
    }
}
