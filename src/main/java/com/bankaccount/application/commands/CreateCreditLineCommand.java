package com.bankaccount.application.commands;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class CreateCreditLineCommand extends BaseCommand<String> {

  @Setter
  @Getter
  private Double maximumOverDrawAmount;

  public CreateCreditLineCommand(String id, Double maximumOverDrawAmount, Date createdAt) {

    super(id);
    this.maximumOverDrawAmount = maximumOverDrawAmount;
    this.createdAt = createdAt;

  }

}
