package com.engineer.library.repository;

import com.engineer.library.model.Book;
import com.engineer.library.model.GuestBook;
import com.engineer.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestBook, Integer> {
    List<GuestBook> findByUser(User user);
    List<GuestBook> findByBook(Book book);
    Optional<GuestBook> findByUserIdAndBookId(int user_id, int book_id);

}
