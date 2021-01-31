package com.accountapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountBalanceRepo extends JpaRepository<AccountBalanceEntity, String> {

  @Query("select acctbal from AccountBalanceEntity acctbal where acctbal.accountNumber = ?1")
  AccountBalanceEntity findBy(String accountNumber);


//  @Query(value = "select count (*) from ACCT_BALANCE",nativeQuery = true)
//  Integer findCount();


}