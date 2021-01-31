package com.accountapi.repository;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="ACCT_BALANCE")
public class AccountBalanceEntity implements Serializable {

      @Id
      @Column(name = "ACCOUNTNUMBER")
      private String accountNumber;

      @Column(name="BALANCE")
      private Float balance;

      @Column(name="TRANSTIMESTAMP")
      private Timestamp transactionTS;

}
