package com.books.books.service;

import com.books.books.entity.BookEntity;
import com.books.books.entity.UserEntity;
import com.books.books.exception.bookExceptions.BookAlreadyExistsException;
import com.books.books.model.bookModels.BookAddModel;
import com.books.books.repository.BookRepository;
import com.books.books.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepo;

    public void addBookToDatabase(BookAddModel bookData) throws BookAlreadyExistsException {
        UserEntity currentUser = userService.getCurrentUser();
        BookEntity book = bookRepo.findByIsbn(bookData.getIsbn());
        if(book != null) {
            throw new BookAlreadyExistsException("book with such isbn already exists");
        }
        book = generateBookEntity(bookData);
        bookRepo.save(book);
        addBookToUserLiked(book, currentUser);
    }

    private BookEntity generateBookEntity(BookAddModel bookData) {
        BookEntity book = new BookEntity();
        book.setName(bookData.getName());
        book.setIsbn(bookData.getIsbn());
        book.setAuthorName(bookData.getAuthorName());
        return book;
    }

    private void addBookToUserLiked(BookEntity book, UserEntity user) {
        List<BookEntity> userBooks = user.getLikedBooks();
        userBooks.add(book);
        user.setLikedBooks(userBooks);
        userRepo.save(user);
    }
}
