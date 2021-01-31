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
@Table(name="ACCT_TRANSACTION")
public class AccountTransactionEntity implements Serializable {

      @Id
      @Column(name = "TRANSID")
      private String transactionId;

      @Column(name = "ACCOUNTNUMBER")
      private String accountNumber;

      @Column(name="TRANSTIMESTAMP")
      private Timestamp transTimestamp;

      @Column(name="TRANSTYPE")
      private String transType;

      @Column(name="AMOUNT")
      private Float amount;

}
