package com.example.bookstoreapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data
    class LoginDTO
    {
        public String email;
        public String password;
    }

