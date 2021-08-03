package com.bamboo.domain.models;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "post")
@Getter
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idx", unique = true, nullable = false)
  private Long idx;

  @Column(name = "title", columnDefinition = "TEXT", nullable = false)
  private String title;

  @Column(name = "content", columnDefinition = "LONGTEXT", nullable = false)
  private String content;

  @Column(name = "anonymous", nullable = false)
  private boolean anonymous;

  @ManyToOne()
  @JoinColumn(name = "fk_user_id")
  private User user;
}
