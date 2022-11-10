package com.bankaccount.application.service.queries;

import com.bankaccount.application.entity.query.BankAccountQueryEntity;
import java.util.List;

public interface BankAccountQueryService {

  BankAccountQueryEntity getBankAccountInformationBy(String accountNumber);

  List<Object> listEventsByAccountNumber(String accountNumber);

}