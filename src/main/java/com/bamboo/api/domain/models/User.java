package com.bamboo.api.domain.models;

import lombok.Builder;
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

  @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
  private Admin admin;

  @OneToMany(mappedBy = "user")
  private List<Post> posts = new ArrayList<>();

  @Builder
  public User(String name, String email, String profileImage){
    this.name = name;
    this.email = email;
    this.profileImage = profileImage;
  }

  public User update(String name, String email, String profileImage){
    this.name = name;
    this.email = email;
    this.profileImage = profileImage;

    return this;
  }
}
