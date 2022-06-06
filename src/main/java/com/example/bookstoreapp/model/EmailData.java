package com.example.bookstoreapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data
class EmailData {
    private String to;
    private String subject;
    private String body;
}

