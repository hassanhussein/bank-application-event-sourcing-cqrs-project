package com.bankaccount.application.events;

import com.bankaccount.application.aggregate.Status;
import java.util.Date;

public class AccountOverDraftedEvent extends BaseEvent<String> {

  public final Status status;

  public AccountOverDraftedEvent(String id, Status status, Date createdAt) {
    super(id);
    this.status = status;
    this.createdAt = createdAt;
  }

}
