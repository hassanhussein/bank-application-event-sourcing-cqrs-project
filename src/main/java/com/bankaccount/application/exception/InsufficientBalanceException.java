package com.bankaccount.application.exception;

public class InsufficientBalanceException extends Throwable {
  public InsufficientBalanceException(String accountId, double debitAmount) {
    super("Insufficient Balance: Cannot debit " + debitAmount +
        " from account number [" + accountId.toString() + "]");
  }
}
