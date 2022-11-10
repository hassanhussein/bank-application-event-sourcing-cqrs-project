package com.bankaccount.application.events;

import java.util.Date;

public class PaymentCreditedEvent extends BaseEvent<String> {

  public final double creditAmount;

  public final String currency;

  public PaymentCreditedEvent(String id, double creditAmount, String currency, Date createdAt) {
    super(id);
    this.creditAmount = creditAmount;
    this.currency = currency;
    this.createdAt = createdAt;
  }

}