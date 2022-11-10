package com.bankaccount.application.rest.queries;

import com.bankaccount.application.entity.query.BankAccountQueryEntity;
import com.bankaccount.application.service.queries.BankAccountQueryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping(value = "/bank-accounts")
@Api(value = "Account Queries", description = "Account Query Events Endpoint", tags = "Account Queries")
public class BankAccountQueryController {

  @Autowired
  private final BankAccountQueryService accountQueryService;

  public BankAccountQueryController(BankAccountQueryService accountQueryService) {
    this.accountQueryService = accountQueryService;
  }

  @GetMapping("/{accountNumber}/events")
  public List<Object> listEventsByAccountNumber(@PathVariable(value = "accountNumber") String accountNumber) {
    return accountQueryService.listEventsByAccountNumber(accountNumber);
  }

  @GetMapping("/{accountNumber}")
  public BankAccountQueryEntity getBankAccountInformationBy(
      @PathVariable(value = "accountNumber") String accountNumber) {
    return accountQueryService.getBankAccountInformationBy(accountNumber);
  }

}
