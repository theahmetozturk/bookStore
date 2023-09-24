package com.example.bookstore.repositories;

import com.example.bookstore.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> findAllByOrderByCreatedAtDesc();

    Optional<Book> findByIsbn(String isbn);
}
