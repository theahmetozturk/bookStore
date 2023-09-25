package com.example.bookstore.services;

import com.example.bookstore.exceptions.BookNotFoundException;
import com.example.bookstore.exceptions.InsufficientStockException;
import com.example.bookstore.exceptions.MinumumOrderAmountException;
import com.example.bookstore.mappers.OrderMapper;
import com.example.bookstore.model.dto.requests.OrderCreateRequest;
import com.example.bookstore.model.entities.Book;
import com.example.bookstore.model.entities.Order;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    @Autowired
    private final BookRepository bookRepository;

    public List<Order> getAllOrders(Long userId) {
        if (userId == null) {
            throw new RuntimeException("userId cannot be null!!");
        }
        return orderRepository.findAllByOrderByUserIdByUpdateDesc(userId);
    }

    public Order createOrder(OrderCreateRequest request) throws MinumumOrderAmountException, InsufficientStockException {

        if (request.getTotalPrice().compareTo(BigDecimal.valueOf(25))<0) {
            throw new MinumumOrderAmountException("Total price must be at least $25");
        }

        Book book =bookRepository.findByIsbn(request.getIsbn())
                .orElseThrow(()-> new BookNotFoundException("Book not found!! "));

        Integer stock = book.getStockQuantity();
        Integer requestStock = request.getRequestQuantity();

        if (requestStock.compareTo(stock)>0) {
            throw new InsufficientStockException("Insufficient stock for the requested quantity");
        }
        Order order = OrderMapper.mapForCreateOrder(request);
        return orderRepository.save(order);
    }

    public Order getByIdOrder(Long orderId) {
        Order order = orderRepository.findById((orderId))
                .orElseThrow(()-> new RuntimeException("Order not found! "+orderId));
        return order;
    }
}
