package com.books.books.controller;

import com.books.books.exception.bookExceptions.BookAlreadyExistsException;
import com.books.books.model.bookModels.BookAddModel;
import com.books.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Object> addBook(@RequestBody BookAddModel bookData) {
        return ResponseEntity.ok("book was added to database");
    }
}
