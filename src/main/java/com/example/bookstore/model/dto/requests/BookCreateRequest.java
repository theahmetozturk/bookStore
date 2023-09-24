package com.example.bookstore.model.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateRequest {
    private String isbn;
    private String title;
    private String author;
    private BigDecimal price;
    private Integer stockQuantity;

}
