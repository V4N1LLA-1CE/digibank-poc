package com.digibank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class Metadata {

  @Column(updatable = false, name = "created_at")
  private LocalDateTime createdAt;

  @Column(updatable = false, name = "created_by")
  private String createdBy;

  @Column(insertable = false, name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(insertable = false, name = "updated_by")
  private String updatedBy;
}
