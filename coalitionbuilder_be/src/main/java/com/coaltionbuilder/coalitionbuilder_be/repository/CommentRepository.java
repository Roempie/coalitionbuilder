package com.coaltionbuilder.coalitionbuilder_be.repository;

import com.coaltionbuilder.coalitionbuilder_be.model.Comment;
import com.coaltionbuilder.coalitionbuilder_be.model.Post;
import com.coaltionbuilder.coalitionbuilder_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

  Optional<Comment> findById(Integer id);

  List<Comment> findByAuthor(User user);

  List<Comment> findByParentComment(Comment comment);

//  List<Comment> findChildCommentsById(Integer id);

  List<Comment> findByPostAndParentCommentIsNull(Post post);
}
