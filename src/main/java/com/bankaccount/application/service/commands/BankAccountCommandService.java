package com.bankaccount.application.service.commands;

import com.bankaccount.application.rest.commands.dto.OpenAccountDTO;
import com.bankaccount.application.rest.commands.dto.CreateCreditLineDto;
import com.bankaccount.application.rest.commands.dto.DepositPaymentDTO;
import com.bankaccount.application.rest.commands.dto.DebitPaymentDTO;
import java.util.concurrent.CompletableFuture;

public interface BankAccountCommandService {

  public CompletableFuture<Object> createAccount(OpenAccountDTO openAccountDTO);
  public CompletableFuture<String> depositMoneyToAccount(String accountNum, DepositPaymentDTO depositPaymentDTO);
  public CompletableFuture<String> withdrawMoneyFromAccount(String accountNum, DebitPaymentDTO debitPaymentDTO);
  public CompletableFuture<String> createCreditLine(String accountNumber, CreateCreditLineDto creditLineDto);

}
