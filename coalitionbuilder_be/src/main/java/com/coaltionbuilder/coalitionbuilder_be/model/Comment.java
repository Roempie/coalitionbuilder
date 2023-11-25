package com.coaltionbuilder.coalitionbuilder_be.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

  @Id
  @GeneratedValue
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id")
  @JsonIgnore
  private User author;

  private String message;

  @CreationTimestamp
  private LocalDateTime creationDate;

  @ManyToOne(
          fetch = FetchType.EAGER,
          cascade = CascadeType.MERGE
  )
  @JsonBackReference
  @JoinColumn(name = "post_id")
  private Post post;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "parentComment_id")
  @Nullable
  @JsonBackReference
  private Comment parentComment;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "parentComment")
  @JsonIgnore
  @Nullable
  private List<Comment> childComments;



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Comment comment = (Comment) o;
    return id != null && Objects.equals(id, comment.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
