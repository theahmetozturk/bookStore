package com.example.bookstore.repositories;

import com.example.bookstore.model.entities.Order;
import com.example.bookstore.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId ORDER BY o.updatedAt DESC")
    List<Order> findAllByOrderByUserIdByUpdateDesc(Long userId);
}
