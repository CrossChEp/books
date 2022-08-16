package com.books.books.repository;

import com.books.books.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findByIsbn(long isbn);
}
