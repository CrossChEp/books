package com.books.books.controller;

import com.books.books.entity.BookEntity;
import com.books.books.exception.bookExceptions.BookAlreadyExistsException;
import com.books.books.exception.userExceptions.UserHaveNoPermission;
import com.books.books.model.bookModels.BookAddModel;
import com.books.books.model.bookModels.BookUpdateModel;
import com.books.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Object> addBook(@RequestBody BookAddModel bookData) throws BookAlreadyExistsException {
        bookService.addBookToDatabase(bookData);
        return ResponseEntity.ok("book was added to database");
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Object> getBook(@PathVariable long isbn) {
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }

    @GetMapping
    public ResponseEntity<Object> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable long id,
                                             @RequestBody BookUpdateModel newBookData) {
        try {
            bookService.updateBook(id, newBookData);
            return ResponseEntity.ok("book was updated");
        } catch (UserHaveNoPermission exception) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
