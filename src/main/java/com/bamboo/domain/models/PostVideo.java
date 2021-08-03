package com.bamboo.domain.models;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "post_video")
@Getter
public class PostVideo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idx", unique = true, nullable = false)
  private long idx;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_post_idx")
  private Post post;
}
