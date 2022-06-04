package com.example.bookstoreapp.dto;


import lombok.Data;

import java.time.LocalDate;

public @Data
class OrderDTO
{
    public int userId;
    public int bookId;
    public String address;

}

