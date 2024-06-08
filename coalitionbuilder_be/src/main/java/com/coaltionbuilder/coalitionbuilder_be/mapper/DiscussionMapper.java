package com.coaltionbuilder.coalitionbuilder_be.mapper;

import com.coaltionbuilder.coalitionbuilder_be.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiscussionMapper {

    DiscussionMapper INSTANCE = Mappers.getMapper(DiscussionMapper.class);

    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);

    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "author", source = "author", qualifiedByName = "mapAuthor")
    PostDto PostToPostDto(Post post);
    Post PostDtoToPost(PostDto postDto);

    @Named("toDtoWithoutChildren")
    @Mapping(target = "childComments", ignore = true)
    CommentDto toDtoWithoutChildren(Comment comment);

    @Named("toDtoWithChildren")
    @Mapping(target = "childComments", qualifiedByName = "toDtoWithChildren")
    CommentDto CommentDtoToComment(Comment comment);

    Comment CommentDtoToComment(CommentDto commentDto);

}