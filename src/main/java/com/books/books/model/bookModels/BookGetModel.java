package com.books.books.model.bookModels;

import com.books.books.entity.BookEntity;

public class BookGetModel {
    private long id;
    private String name;
    private long isbn;
    private String authorName;

    public static BookGetModel toModel(BookEntity book) {
        BookGetModel model = new BookGetModel();
        model.setId(book.getId());
        model.setName(book.getName());
        model.setIsbn(book.getIsbn());
        model.setAuthorName(book.getAuthorName());
        return model;
    }

    public BookGetModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
