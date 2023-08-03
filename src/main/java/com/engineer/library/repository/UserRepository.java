package com.engineer.library.repository;

import com.engineer.library.model.Book;
import com.engineer.library.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

// Custom method to find users by role_id (case-insensitive)
Page<User> findByRoleId(int role_id, Pageable pageable);
}
