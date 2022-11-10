package com.bankaccount.application.rest.commands.dto;

import lombok.Data;

@Data
public class DepositPaymentDTO {

  private Double creditAmount;
  private String currency;

}
