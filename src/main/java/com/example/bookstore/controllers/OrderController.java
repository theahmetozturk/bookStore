package com.example.bookstore.controllers;

import com.example.bookstore.exceptions.InsufficientStockException;
import com.example.bookstore.exceptions.MinumumOrderAmountException;
import com.example.bookstore.exceptions.UserNotFoundException;
import com.example.bookstore.mappers.OrderMapper;
import com.example.bookstore.model.dto.requests.OrderCreateRequest;
import com.example.bookstore.model.dto.responses.OrderResponse;
import com.example.bookstore.model.entities.Order;
import com.example.bookstore.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;


    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
        try{
            final List<Order> orders = orderService.getAllOrders(userId);
            final List<OrderResponse> responses = OrderMapper.orderGetResponse(orders);
            return ResponseEntity.ok(responses);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found!!" +userId, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById (@PathVariable Long orderId)  {
        Order order = orderService.getByIdOrder(orderId);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createOrder (@RequestBody OrderCreateRequest request) throws InsufficientStockException,MinumumOrderAmountException{
        try {
            final Order order = orderService.createOrder(request);
            final OrderResponse response = OrderMapper.orderGetResponse(order);

            return ResponseEntity.ok(response);
        } catch (InsufficientStockException e) {
            return new ResponseEntity<>("Stock not enough",HttpStatus.BAD_REQUEST);
        } catch (MinumumOrderAmountException e) {
            return new ResponseEntity<>("Total price must be at least $25",HttpStatus.BAD_REQUEST);
        }
    }

}
