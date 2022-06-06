package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.OrderDTO;
import com.example.bookstoreapp.exceptions.UserRegistrationCustomException;
import com.example.bookstoreapp.model.BookData;
import com.example.bookstoreapp.model.CartData;
import com.example.bookstoreapp.model.OrderData;
import com.example.bookstoreapp.model.UserRegistrationData;
import com.example.bookstoreapp.repository.OrderRepository;
import com.example.bookstoreapp.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ICartService iCartService;

    @Autowired
    private TokenUtil tokenUtil;

    /***
     * Implemented placeOrder method to place orders
     * @param orderDTO - passing orderDTO param
     * @return
     */
    @Override
    public OrderData placeOrder(OrderDTO orderDTO) {
        CartData cartData = iCartService.getCartById(orderDTO.getCartId());
        int totalPrice = cartData.getTotalPrice();
        OrderData orderData = new OrderData(cartData, orderDTO);
        orderData.setTotalPrice(totalPrice);
        return orderRepository.save(orderData);
    }

    /***
     * Implemented getAllOrders method to find all orders
     * @return
     */
    @Override
    public List<OrderData> getAllOrders() {
        return orderRepository.findAll();
    }

    /***
     * Implemented getOrderById method to find order by id
     * @param orderId - passing orderId param
     * @return
     */
    @Override
    public OrderData getOrderById(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new UserRegistrationCustomException("Order with id " + orderId + " not found"));
    }

    /***
     * Implemented cancelOrder method to cancel an order
     * @param orderId - passing orderId param
     * @return
     */
    @Override
    public OrderData cancelOrder(int orderId) {
        OrderData orderData = this.getOrderById(orderId);
        orderData.setCancel(true);
        return orderRepository.save(orderData);
    }

    /***
     * Implemented verifyOrder method to get the details of order
     * @param token - passing token param
     * @return
     */
    @Override
    public OrderData verifyOrder(String token) {
        OrderData orderData = this.getOrderById(tokenUtil.decodeToken(token));
        return orderRepository.save(orderData);
    }
}
