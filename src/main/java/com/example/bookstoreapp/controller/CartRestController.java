package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.model.CartData;
import com.example.bookstoreapp.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartRestController {
    @Autowired
    private ICartService iCartService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<ResponseDTO> findAllCarts() {
        Iterable<CartData> allCarts = iCartService.findAllCarts();
        ResponseDTO responseDTO = new ResponseDTO("All Items in Carts", allCarts);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get_by_id/{cartId}")
    public ResponseEntity<ResponseDTO> getCartById(@PathVariable("cartId") int cartId) {
        CartData cartData = iCartService.getCartById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Get Cart call Success for Id", cartData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/add_cart")
    public ResponseEntity<ResponseDTO> addToCart(@RequestBody CartDTO cartDTO) {
        CartData cartData = iCartService.addToCart(cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("Product Added To Cart ", cartData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update_quantity/{cartId}")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@PathVariable("cartId") int cartId,
                                                          @RequestParam(value = "quantity") int quantity) {
        CartData cartData = iCartService.updateCartQuantity(cartId, quantity);
        ResponseDTO responseDTO = new ResponseDTO("Update quantity call success for Id", cartData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete_cart/{cartId}")
    public ResponseEntity<ResponseDTO> deleteCart(@PathVariable("cartId") int cartId) {
        iCartService.deleteCart(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Delete call success for Id", "Deleted cart id : " + cartId);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
