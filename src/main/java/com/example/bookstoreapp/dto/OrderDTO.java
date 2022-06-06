package com.example.bookstoreapp.dto;


import lombok.Data;

import java.time.LocalDate;

public @Data class OrderDTO
{
    public int cartId;
    public String address;
}



