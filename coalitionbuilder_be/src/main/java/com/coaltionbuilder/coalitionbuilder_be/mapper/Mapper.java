package com.coaltionbuilder.coalitionbuilder_be.mapper;

import com.coaltionbuilder.coalitionbuilder_be.model.*;

public class Mapper {

    public PostDto map(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setAuthor(post.getAuthor());
        postDto.setCreationDate(post.getCreationDate());
        postDto.setComments(post.getComments());

        return postDto;
    }

    public Post map(PostDto postDto) {
        return Post.builder()
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .author(postDto.getAuthor())
                .creationDate(postDto.getCreationDate())
                .comments(postDto.getComments())
                .build();
    }

    public User map(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .comments(userDto.getComments())
                .posts(userDto.getPosts())
                .role(userDto.getRole())
                .build();
    }

    public UserDto map(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setComments(user.getComments());
        userDto.setPosts(user.getPosts());
        userDto.setRole(user.getRole());

        return userDto;
    }

    public CommentDto map(Comment comment) {
        if (comment == null)
            return null;

        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setAuthor(comment.getAuthor());
        commentDto.setCreationDate(comment.getCreationDate());
        commentDto.setMessage(comment.getMessage());
        commentDto.setParentComment(this.map(comment.getParentComment()));
        commentDto.setPost(comment.getPost());
        commentDto.setChildComments(
                comment.getChildComments().stream().map(this::map).toList()
        );

        return commentDto;
    }
}
