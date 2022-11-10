package com.bankaccount.application.aggregate;

import com.bankaccount.application.commands.OpenAccountCommand;
import com.bankaccount.application.commands.CreateCreditLineCommand;
import com.bankaccount.application.commands.CreditPaymentCommand;
import com.bankaccount.application.commands.DebitPaymentCommand;
import com.bankaccount.application.events.AccountOpenedEvent;
import com.bankaccount.application.events.BankAccountActivatedEvent;
import com.bankaccount.application.events.AccountOverDraftedEvent;
import com.bankaccount.application.events.CreditLineCreatedEvent;
import com.bankaccount.application.events.PaymentCreditedEvent;
import com.bankaccount.application.events.PaymentDebitedEvent;
import com.bankaccount.application.exception.InsufficientBalanceException;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Setter
@Getter
@Aggregate
public class BankAccountAggregate {

  @AggregateIdentifier
  private String id;

  private Double accountBalance;

  private String currency;

  private String status;

  private Double maximumOverDrawAmount;

  private Date createdAt;

  /* Default Constructor used by axon aggregate class */
  public BankAccountAggregate() {
  }

  @CommandHandler
  public BankAccountAggregate(OpenAccountCommand openAccountCommand) {
    AggregateLifecycle.apply(new AccountOpenedEvent(openAccountCommand.id,
        openAccountCommand.accountBalance,
        openAccountCommand.currency, openAccountCommand.maximumOverDrawAmount,
        openAccountCommand.getCreatedAt()));
  }

  @EventSourcingHandler
  protected void on(AccountOpenedEvent accountOpenedEvent) {
    this.id = accountOpenedEvent.id;
    this.accountBalance = accountOpenedEvent.accountBalance;
    this.currency = accountOpenedEvent.currency;
    this.status = String.valueOf(Status.CREATED);
    this.maximumOverDrawAmount = accountOpenedEvent.maximumOverDrawAmount;
    AggregateLifecycle.apply(new BankAccountActivatedEvent(this.id, Status.ACTIVATED, new Date()));
  }

  @EventSourcingHandler
  protected void on(BankAccountActivatedEvent accountActivatedEvent) {
    this.status = String.valueOf(accountActivatedEvent.status);
  }

  @CommandHandler
  protected void handle(CreateCreditLineCommand creditLineCommand) {

    AggregateLifecycle.apply(
        new AccountOpenedEvent(
            this.id,
            this.accountBalance,
            this.currency,
            creditLineCommand.getMaximumOverDrawAmount(),
            new Date()
        )
    );

    AggregateLifecycle.apply(new CreditLineCreatedEvent(creditLineCommand.id,
        creditLineCommand.getMaximumOverDrawAmount(), new Date()));
  }

  @EventSourcingHandler
  protected void on(CreditLineCreatedEvent creditLineCreatedEvent) {

    this.maximumOverDrawAmount = creditLineCreatedEvent.getMaximumOverDrawAmount();

  }

  @CommandHandler
  protected void handle(CreditPaymentCommand creditPaymentCommand) {
    AggregateLifecycle.apply(new PaymentCreditedEvent(creditPaymentCommand.id,
        creditPaymentCommand.creditAmount, creditPaymentCommand.currency, new Date()));
  }

  @EventSourcingHandler
  protected void on(PaymentCreditedEvent paymentCreditedEvent) {

    if (this.accountBalance < 0 && (this.accountBalance + paymentCreditedEvent.creditAmount) >= 0) {
      AggregateLifecycle.apply(new BankAccountActivatedEvent(this.id, Status.ACTIVATED, new Date()));
    }

    this.accountBalance += paymentCreditedEvent.creditAmount;
  }

  @CommandHandler
  protected void handle(DebitPaymentCommand debitPaymentCommand) {
    AggregateLifecycle.apply(new PaymentDebitedEvent(debitPaymentCommand.id,
        debitPaymentCommand.debitAmount, debitPaymentCommand.currency, new Date()));
  }

  @EventSourcingHandler
  protected void on(PaymentDebitedEvent paymentDebitedEvent) throws InsufficientBalanceException {

    double totalCreditLineAmount;

    if ((this.accountBalance - paymentDebitedEvent.debitAmount) < 0) {

      AggregateLifecycle.apply(new AccountOverDraftedEvent(this.id, Status.OVER_DRAFT_STATE, new Date()));

      //Check
      totalCreditLineAmount = this.accountBalance - paymentDebitedEvent.debitAmount;

      if ((totalCreditLineAmount * -1) > this.maximumOverDrawAmount) {
        throw new InsufficientBalanceException(paymentDebitedEvent.id,
            paymentDebitedEvent.debitAmount);
      }

    }

    this.accountBalance -= paymentDebitedEvent.debitAmount;

  }

  @EventSourcingHandler
  protected void on(AccountOverDraftedEvent accountOverDraftedEvent) {
    this.status = String.valueOf(accountOverDraftedEvent.status);
  }


}