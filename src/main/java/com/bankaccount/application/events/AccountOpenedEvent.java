package com.bankaccount.application.events;

import java.util.Date;

public class AccountOpenedEvent extends BaseEvent<String> {

  public final double accountBalance;

  public final String currency;

  public Double maximumOverDrawAmount;

  public AccountOpenedEvent(String id, double accountBalance, String currency,
                            Double maximumOverDrawAmount, Date createdAt) {
    super(id);
    this.accountBalance = accountBalance;
    this.currency = currency;
    this.maximumOverDrawAmount = maximumOverDrawAmount;
    this.createdAt = createdAt;
  }

}