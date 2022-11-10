package com.bankaccount.application.entity.query;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class BankAccountQueryEntity {

  @Id
  private String id;

  private double accountBalance;

  private String currency;

  private String status;

  private Date createdAt;

  @Setter
  @Getter
  public Double maximumOverDrawAmount;

  public BankAccountQueryEntity() {
  }

  @Override
  public String toString() {
    return "BankAccountQueryEntity{" +
        "id='" + id + '\'' +
        ", balance =" + accountBalance +
        ", currency='" + currency + '\'' +
        ", status='" + status + '\'' +
        ", maximumOverDrawAmount='" + maximumOverDrawAmount + '\'' +
        ", createdAt='" + createdAt + '\'' +

        '}';
  }
}
