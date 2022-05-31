package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.model.UserRegistrationData;
import com.example.bookstoreapp.service.IUserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user_registration")
public class UserRegistrationRestController {
    @Autowired
    private IUserRegistrationService iUserRegistrationService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<com.example.bookstoreapp.controller.dto.ResponseDTO> getUserRegistrationData() {
        List<UserRegistrationData> userRegistrationDataList = iUserRegistrationService.getUserRegistrationData();
        com.example.bookstoreapp.controller.dto.ResponseDTO responseDTO = new com.example.bookstoreapp.controller.dto.ResponseDTO("Get Call Success", userRegistrationDataList);
        return new ResponseEntity<com.example.bookstoreapp.controller.dto.ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get_by_id/{userId}")
    public ResponseEntity<com.example.bookstoreapp.controller.dto.ResponseDTO> getUserRegistrationDataById(@PathVariable("userId") int userId) {
        UserRegistrationData userRegistrationData = iUserRegistrationService.getUserRegistrationDataByUserId(userId);
        com.example.bookstoreapp.controller.dto.ResponseDTO responseDTO = new com.example.bookstoreapp.controller.dto.ResponseDTO("Get Call Success for Id", userRegistrationData);
        return new ResponseEntity<com.example.bookstoreapp.controller.dto.ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get_by_email/{email}")
    public ResponseEntity<com.example.bookstoreapp.controller.dto.ResponseDTO> getUserByEmailId(@PathVariable("email") String email) {
        UserRegistrationData userRegistrationData = iUserRegistrationService.getUserByEmailId(email);
        com.example.bookstoreapp.controller.dto.ResponseDTO responseDTO = new com.example.bookstoreapp.controller.dto.ResponseDTO("Get Call Success for Email Id", userRegistrationData);
        return new ResponseEntity<com.example.bookstoreapp.controller.dto.ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<com.example.bookstoreapp.controller.dto.ResponseDTO> createUserRegistrationData(@Valid @RequestBody com.example.bookstoreapp.controller.dto.UserRegistrationDTO userRegistrationDTO) {
        UserRegistrationData userRegistrationData = iUserRegistrationService.createUserRegistrationData(userRegistrationDTO);
        com.example.bookstoreapp.controller.dto.ResponseDTO responseDTO = new com.example.bookstoreapp.controller.dto.ResponseDTO("Created User Registration Data", userRegistrationData);
        return new ResponseEntity<com.example.bookstoreapp.controller.dto.ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<com.example.bookstoreapp.controller.dto.ResponseDTO> updateUserRegistrationDate(@PathVariable("userId") int userId,
                                                                                                          @Valid @RequestBody com.example.bookstoreapp.controller.dto.UserRegistrationDTO userRegistrationDTO) {
        UserRegistrationData userRegistrationData = iUserRegistrationService.updateUserRegistrationData(userId, userRegistrationDTO);
        com.example.bookstoreapp.controller.dto.ResponseDTO responseDTO = new com.example.bookstoreapp.controller.dto.ResponseDTO("Updated User Registration Data for Id", userRegistrationData);
        return new ResponseEntity<com.example.bookstoreapp.controller.dto.ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
