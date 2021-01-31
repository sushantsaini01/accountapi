package com.accountapi.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AccountTransactions {

  //in an arraylist --
  private Float transAmount;

  private Timestamp transTime;

  private String transType;

}
