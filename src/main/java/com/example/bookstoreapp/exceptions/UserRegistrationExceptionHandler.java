package com.example.bookstoreapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class UserRegistrationExceptionHandler {
    private static final String message = "Exception while processing REST request";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<com.example.bookstoreapp.controller.dto.ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errorMessage = errorList.stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        com.example.bookstoreapp.controller.dto.ResponseDTO responseDTO = new com.example.bookstoreapp.controller.dto.ResponseDTO(message, errorMessage);
        return new ResponseEntity<com.example.bookstoreapp.controller.dto.ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserRegistrationCustomException.class)
    public ResponseEntity<com.example.bookstoreapp.controller.dto.ResponseDTO> handleUserRegistrationCustomException(UserRegistrationCustomException exception) {
        com.example.bookstoreapp.controller.dto.ResponseDTO responseDTO = new com.example.bookstoreapp.controller.dto.ResponseDTO(message, exception.getMessage());
        return new ResponseEntity<com.example.bookstoreapp.controller.dto.ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
