package com.accountapi.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class AccountHistoryResponse extends AccountAPIResponseVO{

  private String accountNumber;

  private String transCount;

  private Timestamp paginationTime;

  private List<AccountTransactions> accountTransactions;


}
