package com.example.bookstore.model.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookSavedResponse {
    private Long id;
    private String title;
    private String author;
    private BigDecimal price;
    private Integer stockQuantity;
    private String isbn;
}
