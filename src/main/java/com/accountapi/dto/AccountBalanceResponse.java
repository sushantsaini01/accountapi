package com.accountapi.dto;

import lombok.Data;

@Data
public class AccountBalanceResponse {

  private String accountNumber;

  private Float accountBalance;

}
