package com.engineer.library.repository;

import com.engineer.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    public List<Todo> findByUsername(String username);
}
