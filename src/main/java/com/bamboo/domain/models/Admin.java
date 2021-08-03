package com.bamboo.domain.models;

import com.bamboo.global.enums.AdminEnums;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@Getter
public class Admin {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idx", unique = true, nullable = false)
  private Long idx;

  @Column(name = "permission", nullable = false)
  private AdminEnums permission;

  @OneToOne()
  @JoinColumn(name = "fk_user_id")
  private User user;

}
