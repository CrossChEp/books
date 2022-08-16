package com.books.books.model.bookModels;

import com.books.books.entity.BookEntity;

public class BookAddModel {
    private String name;
    private long isbn;
    private String authorName;

    public static BookAddModel toModel(BookEntity book) {
        BookAddModel model = new BookAddModel();
        model.setName(book.getName());
        model.setIsbn(book.getIsbn());
        model.setAuthorName(book.getAuthorName());
        return model;
    }

    public BookAddModel() {
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
