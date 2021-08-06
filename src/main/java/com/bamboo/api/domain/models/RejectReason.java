package com.bamboo.api.domain.models;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reaject_reason")
@Getter
public class RejectReason {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idx", unique = true, nullable = false)
  private Long idx;

  @Column(name = "reason", nullable = false)
  private String reason;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "rejectReason")
  private List<Post> posts = new ArrayList<>();
}
