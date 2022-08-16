package com.books.books.service;

import com.books.books.entity.BookEntity;
import com.books.books.entity.UserEntity;
import com.books.books.exception.bookExceptions.BookAlreadyExistsException;
import com.books.books.exception.userExceptions.UserHaveNoPermission;
import com.books.books.model.bookModels.BookAddModel;
import com.books.books.model.bookModels.BookGetModel;
import com.books.books.model.bookModels.BookUpdateModel;
import com.books.books.repository.BookRepository;
import com.books.books.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        List<BookEntity> userBooks = user.getAddedBooks();
        userBooks.add(book);
        user.setAddedBooks(userBooks);
        userRepo.save(user);
    }

    public BookGetModel getBookByIsbn(long isbn) {
        BookEntity book = bookRepo.findByIsbn(isbn);
        return BookGetModel.toModel(book);
    }

    public List<BookGetModel> getAllBooks() {
        List<BookEntity> books = bookRepo.findAll();
        return generateBooksModelList(books);
    }

    private List<BookGetModel> generateBooksModelList(List<BookEntity> books) {
        List<BookGetModel> booksModels = new ArrayList<>();
        for(var book: books) {
            booksModels.add(BookGetModel.toModel(book));
        }
        return booksModels;
    }

    public void updateBook(long id, BookUpdateModel newBookData) throws UserHaveNoPermission {
        BookEntity book = bookRepo.findById(id).orElseThrow();
        UserEntity currentUser = userService.getCurrentUser();
        if(!currentUser.getAddedBooks().contains(book)) {
            throw new UserHaveNoPermission("User have no such book");
        }
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(newBookData, book);
        bookRepo.save(book);
    }

    public void deleteBook(long bookId) throws UserHaveNoPermission {
        BookEntity book = bookRepo.findById(bookId).orElseThrow();
        UserEntity user = userService.getCurrentUser();
        if(!user.getAddedBooks().contains(book)) {
            throw new UserHaveNoPermission("User have no such book");
        }
        bookRepo.delete(book);
    }
}
