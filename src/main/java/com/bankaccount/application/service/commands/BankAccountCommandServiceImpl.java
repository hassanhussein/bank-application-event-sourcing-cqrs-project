package com.bankaccount.application.service.commands;

import com.bankaccount.application.commands.OpenAccountCommand;
import com.bankaccount.application.commands.CreateCreditLineCommand;
import com.bankaccount.application.commands.CreditPaymentCommand;
import com.bankaccount.application.commands.DebitPaymentCommand;
import com.bankaccount.application.rest.commands.dto.OpenAccountDTO;
import com.bankaccount.application.rest.commands.dto.CreateCreditLineDto;
import com.bankaccount.application.rest.commands.dto.DepositPaymentDTO;
import com.bankaccount.application.rest.commands.dto.DebitPaymentDTO;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
public class BankAccountCommandServiceImpl implements BankAccountCommandService {

  private final CommandGateway commandGateway;

  public BankAccountCommandServiceImpl(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @Override
  public CompletableFuture<Object> createAccount(OpenAccountDTO accountCreateDTO) {
    return commandGateway.send(new OpenAccountCommand(UUID.randomUUID().toString(), accountCreateDTO.getStartingBalance(),
        accountCreateDTO.getCurrency(), accountCreateDTO.getMaximumOverDrawAmount(), new Date()));
  }

  @Override
  public CompletableFuture<String> depositMoneyToAccount(String accountNumber, DepositPaymentDTO moneyCreditDTO) {
    return commandGateway.send(new CreditPaymentCommand(accountNumber, moneyCreditDTO.getCreditAmount(), moneyCreditDTO.getCurrency(), new Date()));
  }

  @Override
  public CompletableFuture<String> withdrawMoneyFromAccount(String accountNumber, DebitPaymentDTO debitPaymentDTO) {
    return commandGateway.send(new DebitPaymentCommand(accountNumber, debitPaymentDTO.getDebitAmount(), debitPaymentDTO.getCurrency(), new Date()));
  }

  @Override
  public CompletableFuture<String> createCreditLine(String accountNumber, CreateCreditLineDto creditLineDto) {
    return commandGateway.send(new CreateCreditLineCommand(accountNumber,
        creditLineDto.getMaximumOverDrawAmount(), new Date()));
  }

}
