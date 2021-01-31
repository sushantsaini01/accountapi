package com.accountapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;

@Repository
public interface AccountTransactionRepo extends PagingAndSortingRepository<AccountTransactionEntity, String> {

  @Query("select accttrans from AccountTransactionEntity accttrans where accttrans.accountNumber = ?1 and accttrans.transType = ?2 and accttrans.transTimestamp>?3 and accttrans.transTimestamp<=?4 order by accttrans.transTimestamp")
  Page<AccountTransactionEntity> findAllBy(String accountNumber, String transType, Timestamp transStartTime,
                                           Timestamp transEndTime, Pageable pageable);

  @Query("select accttrans from AccountTransactionEntity accttrans where accttrans.accountNumber = ?1 and accttrans.transTimestamp>?2 and accttrans.transTimestamp<=?3 order by accttrans.transTimestamp ")
  Page<AccountTransactionEntity> findAllBy(String accountNumber, Timestamp transStartTime, Timestamp transEndTime,
                                           Pageable pageable);

}