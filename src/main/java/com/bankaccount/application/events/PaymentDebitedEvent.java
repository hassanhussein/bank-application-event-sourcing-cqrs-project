package com.bankaccount.application.events;

import java.util.Date;

public class PaymentDebitedEvent extends BaseEvent<String> {

  public final double debitAmount;

  public final String currency;

  public PaymentDebitedEvent(String id, double debitAmount, String currency, Date createdAt) {
    super(id);
    this.debitAmount = debitAmount;
    this.currency = currency;
    this.createdAt = createdAt;
  }

}