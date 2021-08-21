package com.bamboo.api.domain.models;

import com.bamboo.api.global.enums.RoleEnum;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  private String id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "permission", nullable = false)
  @Enumerated(value = EnumType.STRING)
  private RoleEnum permission;

  @Column(name = "profile_image", nullable = true)
  private String profileImage;

  @OneToMany(mappedBy = "user")
  private List<Post> posts = new ArrayList<>();

  @Builder
  public User(String id, String name, String email, RoleEnum permission, String profileImage){
    this.id = id;
    this.name = name;
    this.email = email;
    this.permission = permission;
    this.profileImage = profileImage;
  }

  public User update(String name, String email, String profileImage){
    this.name = name;
    this.email = email;
    this.profileImage = profileImage;

    return this;
  }
}
