package com.example.bookstore.repositories;

import com.example.bookstore.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> findAll();
    default List<Book> findAllByOrderByCreatedAtDesc(){
        return findAll().stream()
                .sorted(Comparator.comparing(Book::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    Optional<Book> findByIsbn(String isbn);
}
