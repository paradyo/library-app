package com.engineer.library.repository;

import com.engineer.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    // Custom method to find books by title
    Page<Book> findByName(String name, Pageable pageable);

    // Custom method to find books by author
    Page<Book> findByAuthor(String author, Pageable pageable);

    // Custom method to find books by visibility
    Page<Book> findByIsVisible(boolean isVisible, Pageable pageable);

    // Custom method to find books by both title and author
    @Query("SELECT b FROM Book b WHERE b.name = :name AND b.author = :author")
    Page<Book> findByNameAndAuthor(String name, String author, Pageable pageable);

    // Custom method to search books by name (case-insensitive)
    Page<Book> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Custom method to search books by author (case-insensitive)
    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);

    // Custom method to search books by title or author (case-insensitive)
    Page<Book> findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(String name, String author, Pageable pageable);
}
