package com.example.bookstore.mappers;

import com.example.bookstore.model.dto.requests.OrderCreateRequest;
import com.example.bookstore.model.dto.responses.OrderResponse;
import com.example.bookstore.model.entities.Book;
import com.example.bookstore.model.entities.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static Order mapForCreateOrder(OrderCreateRequest request) {
        return Order.builder()
                .user(request.getUser())
                .books(request.getBooks())
                .totalPrice(request.getTotalPrice())
                .build();
    }

    public static OrderResponse orderGetResponse (Order order) {
        return OrderResponse.builder()
                .books(order.getBooks())
                .orderId(order.getOrderId())
                .totalPrice(order.getTotalPrice())
                .user(order.getUser())
                .build();

    }
    public static List<OrderResponse> orderGetResponse(List<Order> orders) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order: orders) {
            orderResponses.add(orderGetResponse(order));
        }
        return orderResponses;
    }
}
