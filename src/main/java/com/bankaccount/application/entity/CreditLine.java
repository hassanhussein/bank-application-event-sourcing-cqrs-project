package com.bankaccount.application.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "credit_lines")
@AllArgsConstructor
@NoArgsConstructor
public class CreditLine {
  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  private Date createdAt;

  private UUID accountId;

  private BigDecimal overDrawAmountLimit;

}