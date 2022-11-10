package com.bankaccount.application.service.queries;

import com.bankaccount.application.entity.query.BankAccountQueryEntity;
import com.bankaccount.application.entity.repository.BankAccountRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountQueryServiceImpl implements BankAccountQueryService {

  private final EventStore eventStore;

  @Autowired
  private BankAccountRepository bankAccountRepository;

  public BankAccountQueryServiceImpl(EventStore eventStore) {
    this.eventStore = eventStore;
  }

  @Override
  public BankAccountQueryEntity getBankAccountInformationBy(String accountNumber) {

    return bankAccountRepository.findById(accountNumber).get();

  }

  @Override
  public List<Object> listEventsByAccountNumber(String accountNumber) {
    return this.eventStore.readEvents(accountNumber).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
  }

}