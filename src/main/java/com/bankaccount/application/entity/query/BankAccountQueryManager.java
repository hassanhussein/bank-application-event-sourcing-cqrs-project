package com.bankaccount.application.entity.query;

import com.bankaccount.application.aggregate.BankAccountAggregate;
import com.bankaccount.application.entity.repository.BankAccountRepository;
import com.bankaccount.application.entity.repository.CreditLineRepository;
import com.bankaccount.application.events.BaseEvent;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BankAccountQueryManager {

  @Autowired
  private BankAccountRepository bankAccountRepository;

  @Autowired
  private CreditLineRepository creditLineRepository;

  @Autowired
  @Qualifier("accountAggregateEventSourcingRepository")
  private EventSourcingRepository<BankAccountAggregate> accountAggregateEventSourcingRepository;

  @EventSourcingHandler
  void on(BaseEvent event){
    persistAccount(buildQueryAccount(getAccountFromEvent(event)));
  }

  private BankAccountQueryEntity buildQueryAccount(BankAccountAggregate accountAggregate){
    BankAccountQueryEntity accountQueryEntity = findExistingOrCreateQueryAccount(accountAggregate.getId());

    accountQueryEntity.setId(accountAggregate.getId());
    accountQueryEntity.setAccountBalance(accountAggregate.getAccountBalance());
    accountQueryEntity.setCurrency(accountAggregate.getCurrency());
    accountQueryEntity.setStatus(accountAggregate.getStatus());
    accountQueryEntity.setMaximumOverDrawAmount((accountAggregate.getMaximumOverDrawAmount()==null)?null:accountAggregate.getMaximumOverDrawAmount());
    accountQueryEntity.setCreatedAt(accountAggregate.getCreatedAt());
    return accountQueryEntity;
  }

  private BankAccountAggregate getAccountFromEvent(BaseEvent event){
    return accountAggregateEventSourcingRepository.load(event.id.toString()).getWrappedAggregate().getAggregateRoot();
  }

  private BankAccountQueryEntity findExistingOrCreateQueryAccount(String id){
    return bankAccountRepository.findById(id).isPresent() ? bankAccountRepository.findById(id).get() : new BankAccountQueryEntity();
  }

  private void persistAccount(BankAccountQueryEntity accountQueryEntity){
    bankAccountRepository.save(accountQueryEntity);
  }

}
