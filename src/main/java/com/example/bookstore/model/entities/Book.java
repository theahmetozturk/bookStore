package com.example.bookstore.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{13}$", message = "ISBN must consist of 13 digits.")
    private String isbn;

    private String title;

    private String author;

    private BigDecimal price;

    private Integer stockQuantity;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private Order order;

}
