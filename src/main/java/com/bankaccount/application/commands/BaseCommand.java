package com.bankaccount.application.commands;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {

  @TargetAggregateIdentifier
  public final T id;

  public BaseCommand(T id) {
    this.id = id;
  }

  @Setter
  @Getter
  public Date createdAt;

}