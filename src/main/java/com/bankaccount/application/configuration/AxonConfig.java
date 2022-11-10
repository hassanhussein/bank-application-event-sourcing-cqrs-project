package com.bankaccount.application.configuration;

import com.bankaccount.application.aggregate.BankAccountAggregate;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

  @Bean
  EventSourcingRepository<BankAccountAggregate> accountAggregateEventSourcingRepository(EventStore eventStore) {
    return EventSourcingRepository.builder(BankAccountAggregate.class).eventStore(eventStore).build();
  }

}