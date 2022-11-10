package com.bankaccount.application.events;

import java.util.Date;

public class BaseEvent<T> {

  public final T id;

  public BaseEvent(T id) {
    this.id = id;
  }

  public Date createdAt;

}