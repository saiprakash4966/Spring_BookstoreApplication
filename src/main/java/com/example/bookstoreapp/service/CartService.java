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
    private IUserRegistrationService iUserRegistrationService;

    @Autowired
    private IBookService iBookService;

    /***
     * Implemented addToCart method to add books and user in the cart
     * @param cartDTO - passing cartDTO param
     * @return
     */
    @Override
    public CartData addToCart(CartDTO cartDTO) {
        UserRegistrationData userRegistrationData = iUserRegistrationService.getUserRegistrationDataByUserId(cartDTO.getUserId());
        if (userRegistrationData != null) {
            BookData bookData = iBookService.getBookById(cartDTO.getBookId());
            int totalPrice = bookData.getBookPrice() * cartDTO.getQuantity();
            CartData cartData = new CartData(userRegistrationData, bookData, cartDTO.quantity, totalPrice);
            return cartRepository.save(cartData);
        }
        return null;
    }

    /***
     * Implemented findAllCarts method to find all the carts
     * @return
     */
    @Override
    public Iterable<CartData> findAllCarts() {
        return cartRepository.findAll();
    }

    /***
     * Implemented getCartById method to find cart by id
     * @param cartId - passing cartId param
     * @return
     */
    @Override
    public CartData getCartById(int cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new UserRegistrationCustomException("Cart with id " + cartId + " not found"));
    }

    /***
     * Implemented updateQuantity method to update book quantity
     * @param cartId - passing cartId param
     * @param quantity - passing quantity param
     * @return
     */
    @Override
    public CartData updateQuantity(int cartId, int quantity) {
        CartData cartData = this.getCartById(cartId);
        cartData.setQuantity(quantity);
        return cartRepository.save(cartData);
    }

    /***
     * Implemented deleteCart method to delete cart by id
     * @param cartId - passing cartId param
     */
    @Override
    public void deleteCart(int cartId) {
        CartData cartData = this.getCartById(cartId);
        cartRepository.delete(cartData);
    }
}

