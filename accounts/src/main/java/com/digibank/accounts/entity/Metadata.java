package com.digibank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Metadata {

  @CreatedDate
  @Column(updatable = false, name = "created_at")
  private LocalDateTime createdAt;

  @CreatedBy
  @Column(updatable = false, name = "created_by")
  private String createdBy;

  @LastModifiedDate
  @Column(insertable = false, name = "updated_at")
  private LocalDateTime updatedAt;

  @LastModifiedBy
  @Column(insertable = false, name = "updated_by")
  private String updatedBy;
}
