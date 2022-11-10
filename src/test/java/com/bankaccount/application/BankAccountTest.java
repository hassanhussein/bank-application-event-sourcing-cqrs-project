package com.bankaccount.application;

import com.bankaccount.application.aggregate.BankAccountAggregate;
import com.bankaccount.application.aggregate.Status;
import com.bankaccount.application.commands.OpenAccountCommand;
import com.bankaccount.application.commands.CreateCreditLineCommand;
import com.bankaccount.application.events.BankAccountActivatedEvent;
import com.bankaccount.application.events.AccountOpenedEvent;
import com.bankaccount.application.events.CreditLineCreatedEvent;
import java.util.Date;
import java.util.UUID;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {

  private FixtureConfiguration<BankAccountAggregate> fixtureConfiguration;
  private String id;
  private Double initialAccountBalance = 5000.00;
  private Double maximumOverDrawAmount = 3000.00;
  private String currency = "USD";

  @BeforeEach
  public void setUp() {
    id = UUID.randomUUID().toString();
    fixtureConfiguration = new AggregateTestFixture<>(BankAccountAggregate.class);
  }

  @Test
  void should_open_and_activate_account_with_initial_deposit() {

    fixtureConfiguration.givenNoPriorActivity()
        .when(new OpenAccountCommand(
            id,
            initialAccountBalance,
            currency,
            maximumOverDrawAmount,
            new Date()
        )).expectSuccessfulHandlerExecution()
        .expectEvents(
            new AccountOpenedEvent(
                id,
                initialAccountBalance,
                currency,
                maximumOverDrawAmount,
                new Date()
            ),
            new BankAccountActivatedEvent(
                id, Status.ACTIVATED, new Date()
            )
        );
  }

  @Test
  void should_add_credit_line_event() {

    fixtureConfiguration.given(new AccountOpenedEvent(id,
            100, Status.ACTIVATED.toString(), maximumOverDrawAmount, new Date()))
        .when(new CreateCreditLineCommand(id, maximumOverDrawAmount, new Date()))
        .expectSuccessfulHandlerExecution()
        .expectEvents(new CreditLineCreatedEvent(id, maximumOverDrawAmount, new Date()), new Date());
  }

  @Test
  void should_dispatch_created_credit_line_event_when_created_credit_line_Command() {
    fixtureConfiguration.given(
        new OpenAccountCommand(
            id,
            initialAccountBalance,
            currency,
            maximumOverDrawAmount, new Date()
        )
    ).when(new CreateCreditLineCommand(
        id,
        maximumOverDrawAmount, new Date()
    )).expectNoEvents();
  }

}
