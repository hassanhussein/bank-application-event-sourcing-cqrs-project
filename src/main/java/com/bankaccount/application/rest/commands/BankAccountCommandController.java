package com.bankaccount.application.rest.commands;

import com.bankaccount.application.rest.commands.dto.OpenAccountDTO;
import com.bankaccount.application.rest.commands.dto.CreateCreditLineDto;
import com.bankaccount.application.rest.commands.dto.DepositPaymentDTO;
import com.bankaccount.application.rest.commands.dto.DebitPaymentDTO;
import com.bankaccount.application.service.commands.BankAccountCommandService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping(value = "/bank-accounts")
@Api(value = "Account Commands", description = "Account Commands Related Endpoints", tags = "Account Commands")
public class BankAccountCommandController {

  private final BankAccountCommandService bankAccountCommandService;

  public BankAccountCommandController(BankAccountCommandService bankAccountCommandService) {
    this.bankAccountCommandService = bankAccountCommandService;
  }

  @PostMapping
  public CompletableFuture<Object> createAccount(@RequestBody OpenAccountDTO accountCreateDTO){
    return bankAccountCommandService.createAccount(accountCreateDTO);
  }

  @PutMapping(value = "/credits/{accountNumber}")
  public CompletableFuture<String> creditMoneyToAccount(@PathVariable(value = "accountNumber") String accountNumber,
                                                        @RequestBody DepositPaymentDTO moneyCreditDTO){
    return bankAccountCommandService.depositMoneyToAccount(accountNumber, moneyCreditDTO);
  }

  @PutMapping(value = "/debits/{accountNumber}")
  public CompletableFuture<String> debitMoneyFromAccount(@PathVariable(value = "accountNumber") String accountNumber,
                                                         @RequestBody DebitPaymentDTO moneyDebitDTO){
    return bankAccountCommandService.withdrawMoneyFromAccount(accountNumber, moneyDebitDTO);
  }

  @PutMapping(value = "/creditLines/{accountNumber}")
  public CompletableFuture<String> createCreditLine(
      @PathVariable(value = "accountNumber") String accountNumber,
      @RequestBody CreateCreditLineDto creditLineDto) {

    return bankAccountCommandService.createCreditLine(accountNumber, creditLineDto);

  }

}
