package com.bamboo.api.domain.models;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag")
@Getter
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idx", unique = true, nullable = false)
  private long idx;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
          name = "tag_to_post",
          joinColumns = @JoinColumn(name = "fk_tag_idx"),
          inverseJoinColumns = @JoinColumn(name = "fk_post_idx")
  )
  private List<Post> posts = new ArrayList<>();
}