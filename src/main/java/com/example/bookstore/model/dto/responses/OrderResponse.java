package com.example.bookstore.model.dto.responses;

import com.example.bookstore.model.entities.Book;
import com.example.bookstore.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private User user;
    private BigDecimal totalPrice;
    private List<Book> books;
    private Long orderId;

}
