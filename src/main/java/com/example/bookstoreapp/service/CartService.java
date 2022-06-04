package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.exceptions.UserRegistrationCustomException;
import com.example.bookstoreapp.model.BookData;
import com.example.bookstoreapp.model.CartData;
import com.example.bookstoreapp.model.UserRegistrationData;
import com.example.bookstoreapp.repository.CartRepository;
import com.example.bookstoreapp.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private IUserRegistrationService iUserRegistrationService;

    @Autowired
    private IBookService iBookService;

    @Override
    public CartData addToCart(CartDTO cartDTO) {
        UserRegistrationData userRegistrationData = iUserRegistrationService.getUserRegistrationDataByUserId(cartDTO.getUserId());
        if (userRegistrationData != null) {
            BookData bookData = iBookService.getBookById(cartDTO.getBookId());
            CartData cartData = new CartData(userRegistrationData, bookData);
            return cartRepository.save(cartData);
        }
        return null;
    }

    @Override
    public Iterable<CartData> findAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public CartData getCartById(int cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new UserRegistrationCustomException("Cart with id " + cartId + " not found"));
    }

//    @Override
//    public CartData updateCartQuantity(int cartId, int quantity) {
//        CartData cartData = this.getCartById(cartId);
//        cartData.setQuantity(quantity);
//        return cartRepository.save(cartData);
//    }

    @Override
    public void deleteCart(int cartId) {
        CartData cartData = this.getCartById(cartId);
        cartRepository.delete(cartData);
    }
}

