package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.model.CartData;
import com.example.bookstoreapp.service.ICartService;
import com.example.bookstoreapp.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartRestController {
    @Autowired
    private ICartService iCartService;

    @Autowired
    private TokenUtil tokenUtil;

    /***
     * Implemented findAllCarts method to get all cart data form database
     * @return
     */
    @GetMapping(value = {"", "/"})
    public ResponseEntity<ResponseDTO> findAllCarts() {
        Iterable<CartData> allCarts = iCartService.findAllCarts();
        ResponseDTO responseDTO = new ResponseDTO("All items in Carts", allCarts);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /***
     * Implemented getCartById method to get cart by id from database
     * @param token - passing token as param
     * @return
     */
    @GetMapping("/get_by_id/{token}")
    public ResponseEntity<ResponseDTO> getCartById(@PathVariable("token") String token) {
        int tokenId = tokenUtil.decodeToken(token);
        CartData cartData = iCartService.getCartById(tokenId);
        ResponseDTO responseDTO = new ResponseDTO("Get Cart call Success for Id", cartData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /***
     * Implemented addToCart method to add cart
     * @param cartDTO - passing cartDTO as param
     * @return
     */
    @PostMapping("/add_cart")
    public ResponseEntity<ResponseDTO> addToCart(@RequestBody CartDTO cartDTO) {
        CartData cartData = iCartService.addToCart(cartDTO);
        String token = tokenUtil.createToken(cartData.getCartId());
        ResponseDTO responseDTO = new ResponseDTO("Product Added To Cart", cartData, token);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
    }

    /***
     * Implemented updateBookQuantity method to update quantity of the books in the cart
     * @param token - passing token as param
     * @param quantity - passing quantity as param
     * @return
     */
    @PutMapping("/update_quantity/{token}")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@PathVariable("token") String token,
                                                          @RequestParam(value = "quantity") int quantity) {
        int tokenId = tokenUtil.decodeToken(token);
        CartData cartData = iCartService.updateQuantity(tokenId, quantity);
        ResponseDTO responseDTO = new ResponseDTO("Updated quantity call success for Id", cartData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /***
     * Implemented deleteCart method to delete the cart from database
     * @param token - passing token as param
     * @return
     */
    @DeleteMapping("/delete_cart/{token}")
    public ResponseEntity<ResponseDTO> deleteCart(@PathVariable("token") String token) {
        int tokenId = tokenUtil.decodeToken(token);
        iCartService.deleteCart(tokenId);
        ResponseDTO responseDTO = new ResponseDTO("Delete call success for Id", "Deleted cart id : " + tokenId);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}

