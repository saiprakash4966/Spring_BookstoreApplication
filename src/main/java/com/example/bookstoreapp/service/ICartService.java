package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.model.CartData;

public interface ICartService {
    CartData addToCart(CartDTO cartDTO);

    Iterable<CartData> findAllCarts();

    CartData getCartById(int cartId);


    void deleteCart(int cartId);
}


