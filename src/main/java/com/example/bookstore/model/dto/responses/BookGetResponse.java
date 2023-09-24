package com.example.bookstore.model.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookGetResponse {
    private String isbn;
    private String name;
    private String title;
    private String author;
    private BigDecimal price;
    private Integer stockQuantity;
}
