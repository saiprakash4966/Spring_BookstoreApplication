package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.OrderDTO;
import com.example.bookstoreapp.exceptions.UserRegistrationCustomException;
import com.example.bookstoreapp.model.BookData;
import com.example.bookstoreapp.model.OrderData;
import com.example.bookstoreapp.model.UserRegistrationData;
import com.example.bookstoreapp.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IUserRegistrationService iUserRegistrationService;

    @Autowired
    private IBookService iBookService;

    @Override
    public OrderData placeOrder(OrderDTO orderDTO) {
        UserRegistrationData userRegistrationData = iUserRegistrationService.getUserRegistrationDataByUserId(orderDTO.getUserId());
        BookData bookData = iBookService.getBookById(orderDTO.getBookId());
        int totalPrice = bookData.getBookPrice() * bookData.getBookQuantity();
        OrderData orderData = new OrderData(userRegistrationData, bookData, orderDTO);
        orderData.setTotalPrice(totalPrice);
        return orderRepository.save(orderData);
    }

    @Override
    public List<OrderData> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public OrderData getOrderById(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new UserRegistrationCustomException("Order with id " + orderId + " not found"));
    }

    @Override
    public OrderData cancelOrder(int orderId) {
        OrderData orderData = this.getOrderById(orderId);
        orderData.setCancel(true);
        return orderRepository.save(orderData);
    }
}

