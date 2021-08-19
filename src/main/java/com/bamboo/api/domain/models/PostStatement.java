package com.bamboo.api.domain.models;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "post_statement")
@Getter
public class PostStatement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idx", unique = true, nullable = false)
  private Long idx;

  @OneToOne
  @JoinColumn(name = "fk_post_idx")
  private Long postIdx;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_reject_reason_idx")
  private RejectReason rejectReason;
}
