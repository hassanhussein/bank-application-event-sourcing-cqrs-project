package com.bankaccount.application.rest.commands.dto;

import lombok.Data;

@Data
public class OpenAccountDTO {

  private Double startingBalance;

  private String owner;

  private String currency;

  private Double maximumOverDrawAmount;

}