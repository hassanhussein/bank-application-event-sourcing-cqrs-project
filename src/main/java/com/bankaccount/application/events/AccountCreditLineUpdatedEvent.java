package com.bankaccount.application.events;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class AccountCreditLineUpdatedEvent extends BaseEvent<String> {

  @Setter
  @Getter
  private Double maximumOverDrawAmount;

  public AccountCreditLineUpdatedEvent(String id, Double maximumOverDrawAmount, Date createdAt) {
    super(id);
    this.maximumOverDrawAmount = maximumOverDrawAmount;
    this.createdAt = createdAt;
  }

}
