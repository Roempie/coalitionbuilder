package com.coaltionbuilder.coalitionbuilder_be.repository;

import com.coaltionbuilder.coalitionbuilder_be.model.Post;
import com.coaltionbuilder.coalitionbuilder_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {

  Optional<Post> findById(Integer id);

  List<Post> findAll();

  List<Post> findByAuthor(User user);



}
