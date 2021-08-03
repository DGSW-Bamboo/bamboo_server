package com.bamboo.domain.models;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
public class User {

  @Id
  private String id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "profile_image", nullable = true)
  private String profileImage;

  @OneToOne(mappedBy = "user")
  private Admin admin;

  @OneToMany(mappedBy = "user")
  private List<Post> posts = new ArrayList<>();
}
