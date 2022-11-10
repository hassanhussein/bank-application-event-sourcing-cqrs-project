package com.bankaccount.application.rest.commands.dto;

import lombok.Data;

@Data
public class CreateCreditLineDto {

  private String accountNumber;

  private Double maximumOverDrawAmount;

}
