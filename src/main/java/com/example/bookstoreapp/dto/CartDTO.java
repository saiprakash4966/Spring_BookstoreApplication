package com.example.bookstoreapp.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data
class CartDTO
{
    public int userId;
    public int bookId;

}

