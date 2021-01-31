package com.accountapi.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class AccountBalanceRequest {

  @Pattern(regexp = "\\d{9}")
  private String accountNumber;


}
