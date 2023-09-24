package com.example.bookstore.mappers;

import com.example.bookstore.model.dto.requests.BookCreateRequest;
import com.example.bookstore.model.dto.responses.BookGetResponse;
import com.example.bookstore.model.dto.responses.BookSavedResponse;
import com.example.bookstore.model.entities.Book;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {
    public static Book mapForSaving(BookCreateRequest request) {
        return Book.builder()
                .title(request.getTitle())
                .isbn(request.getIsbn())
                .author(request.getAuthor())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .build();
    }

    public static Book mapForUpdate(BookCreateRequest request) {
        return Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .build();
    }

    public static BookGetResponse toGetResponse(Book book) {
        return BookGetResponse.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .price(book.getPrice())
                .stockQuantity(book.getStockQuantity())
                .build();
    }

    public static List<BookGetResponse> toGetResponse(List<Book> books) {
        if (books == null)
            return null;
        List<BookGetResponse> responses = new ArrayList<>();

        for (Book book : books) {
            responses.add((BookGetResponse) toGetResponse(book));
        }
        return responses;
    }

    public static BookSavedResponse toSavedResponse(Book book) {
        return BookSavedResponse.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .price(book.getPrice())
                .stockQuantity(book.getStockQuantity())
                .title(book.getTitle())
                .build();
    }
}
