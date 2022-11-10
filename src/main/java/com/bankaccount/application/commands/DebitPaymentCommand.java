package com.bankaccount.application.commands;

import java.util.Date;

public class DebitPaymentCommand extends BaseCommand<String> {

  public final double debitAmount;

  public final String currency;

  public DebitPaymentCommand(String id, double debitAmount, String currency, Date createdAt) {
    super(id);
    this.debitAmount = debitAmount;
    this.currency = currency;
    this.createdAt = createdAt;
  }

}