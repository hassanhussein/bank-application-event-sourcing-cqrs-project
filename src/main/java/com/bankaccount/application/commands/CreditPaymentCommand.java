package com.bankaccount.application.commands;

import java.util.Date;

public class CreditPaymentCommand extends BaseCommand<String> {

  public final double creditAmount;

  public final String currency;

  public CreditPaymentCommand(String id, double creditAmount, String currency, Date createdAt) {
    super(id);
    this.creditAmount = creditAmount;
    this.currency = currency;
    this.createdAt = createdAt;;
  }

}