package com.example.bookstore.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal totalPrice;

    @OneToMany(
            mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST
            }
    )
    private List<Book> books;
}
