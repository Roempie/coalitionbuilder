package com.coaltionbuilder.coalitionbuilder_be.model;

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
@Table(name = "post")
public class Post {

  @Id
  @GeneratedValue
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id")
  @JsonIgnore
  private User author;

  private String title;

  @Column(length = 2048)
  private String description;

  @CreationTimestamp
  private LocalDateTime creationDate;

  @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JsonIgnore
  @Nullable
  private List<Comment> comments;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Post post = (Post) o;
    return id != null && Objects.equals(id, post.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
