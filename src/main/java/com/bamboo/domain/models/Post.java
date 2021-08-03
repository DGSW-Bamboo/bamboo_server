package com.bamboo.domain.models;

import com.bamboo.global.enums.PostStateEnum;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

  @Column(name = "state", nullable = false)
  private PostStateEnum state;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "fk_user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_admin_idx")
  private Admin admin;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_reject_reason_idx")
  private RejectReason rejectReason;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
  private List<PostImage> postImages = new ArrayList<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
  private List<PostVideo> postVideos = new ArrayList<>();

  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "posts")
  private List<Tag> tags = new ArrayList<>();

}
