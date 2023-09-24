package com.example.bookstore.services;

import com.example.bookstore.exceptions.BookNotFoundException;
import com.example.bookstore.mappers.BookMapper;
import com.example.bookstore.model.dto.requests.BookCreateRequest;
import com.example.bookstore.model.entities.Book;
import com.example.bookstore.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    @Autowired
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAllByOrderByCreatedAtDesc();
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book not found; " + isbn));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Book createBook(BookCreateRequest request) {
        Book book = BookMapper.mapForSaving(request);
        return bookRepository.save(book);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Book updateBook(BookCreateRequest request) {
        Book book = BookMapper.mapForUpdate(request);
        return bookRepository.save(book);
    }

   /* @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }*/

   @PreAuthorize("hasRole('ADMIN')")
   public boolean deleteBook(Long id) {
       Book existingBook = bookRepository.findById(id).orElse(null);
       if (existingBook != null) {
           bookRepository.delete(existingBook);
           return true;
       }
       return false;
   }

}

