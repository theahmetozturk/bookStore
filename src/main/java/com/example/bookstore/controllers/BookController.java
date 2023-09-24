package com.example.bookstore.controllers;

import com.example.bookstore.exceptions.BookNotFoundException;
import com.example.bookstore.exceptions.UnauthorizedException;
import com.example.bookstore.mappers.BookMapper;
import com.example.bookstore.model.dto.requests.BookCreateRequest;
import com.example.bookstore.model.dto.responses.BookGetResponse;
import com.example.bookstore.model.dto.responses.BookSavedResponse;
import com.example.bookstore.model.entities.Book;
import com.example.bookstore.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<List<BookGetResponse>> getAllBooks() {
        final List<Book> books = bookService.getAllBooks();
        final List<BookGetResponse> responses = BookMapper.toGetResponse(books);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookGetResponse> getBookByIsbn(@PathVariable("isbn") String isbn) throws BookNotFoundException {
        Book book = bookService.getBookByIsbn(isbn);

        if (book == null) {
            throw new BookNotFoundException("Book not found: " + isbn);
        }

        BookGetResponse bookGetResponse = BookMapper.toGetResponse(book);
        return ResponseEntity.ok(bookGetResponse);
    }

    /*    Book book;
        try {
            book = bookService.getBookByIsbn(isbn);
            if (book != null) {
                BookResponse bookResponse = BookMapper.toGetResponse(book);
                return ResponseEntity.ok(bookResponse);
            }
        } catch (BookNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
        return null;
    */

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookSavedResponse> createBook(@RequestBody BookCreateRequest request) throws UnauthorizedException {
        try {
            final Book book = bookService.createBook(request);
            final BookSavedResponse response = BookMapper.toSavedResponse(book);

            return ResponseEntity.ok(response);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PutMapping("/update/{isbn}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BookSavedResponse> updateBook(@PathVariable("isbn") String isbn, @RequestBody BookCreateRequest request) throws UnauthorizedException, BookNotFoundException {
        if (isbn == null || isbn.isEmpty() || request == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            final Book book = bookService.updateBook(request);
            final BookSavedResponse response = BookMapper.toSavedResponse(book);
            return ResponseEntity.ok(response);
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @DeleteMapping("/delete/{isbn}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable("isbn") String isbn) throws BookNotFoundException,UnauthorizedException{
        if (isbn == null || isbn.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            bookService.deleteBook(Long.valueOf(isbn));
            return ResponseEntity.ok().build();
        } catch (BookNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

