package com.example.bookstore.model.dto.requests;

import com.example.bookstore.model.entities.Book;
import com.example.bookstore.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreateRequest {
    private User user;
    private List<Book> books;
    private String isbn;
    private Integer requestQuantity;
    private BigDecimal totalPrice;
}
