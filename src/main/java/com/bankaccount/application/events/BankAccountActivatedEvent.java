package com.bankaccount.application.events;

import com.bankaccount.application.aggregate.Status;
import java.util.Date;

public class BankAccountActivatedEvent extends BaseEvent<String>{
  public final Status status;

  public BankAccountActivatedEvent(String id, Status status, Date createdAt) {
    super(id);
    this.status = status;
    this.createdAt = createdAt;
  }
}