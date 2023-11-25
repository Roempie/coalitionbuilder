package com.coaltionbuilder.coalitionbuilder_be.repository;

import com.coaltionbuilder.coalitionbuilder_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    void deleteById(Integer id);

    List<User> findAll();
}
