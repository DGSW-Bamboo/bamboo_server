package com.bamboo.api.domain.models;

import com.bamboo.api.global.enums.RoleEnum;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admin")
@Getter
public class Admin {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idx", unique = true, nullable = false)
  private Long idx;

  @Column(name = "permission", nullable = false)
  private RoleEnum permission;

  @OneToOne()
  @JoinColumn(name = "fk_user_id")
  private User user;

  @OneToMany(mappedBy = "admin")
  private List<Post> posts = new ArrayList<>();
}
