package com.bankaccount.application.commands;

import java.util.Date;

public class OpenAccountCommand extends BaseCommand<String> {

  public final double accountBalance;

  public final String currency;

  public final Double maximumOverDrawAmount;

  public OpenAccountCommand(String id, double accountBalance, String currency,
                            Double maximumOverDrawAmount, Date createdAt) {
    super(id);
    this.accountBalance = accountBalance;
    this.currency = currency;
    this.maximumOverDrawAmount = maximumOverDrawAmount;
    this.createdAt = createdAt;
  }

}