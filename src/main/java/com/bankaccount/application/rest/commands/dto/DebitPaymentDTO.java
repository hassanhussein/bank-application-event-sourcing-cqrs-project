package com.bankaccount.application.rest.commands.dto;

import lombok.Data;

@Data
public class DebitPaymentDTO {

  private Double debitAmount;

  private String currency;

}
