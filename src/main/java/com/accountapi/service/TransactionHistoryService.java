package com.accountapi.service;


import com.accountapi.dto.APIStatusVO;
import com.accountapi.dto.AccountHistoryRequest;
import com.accountapi.dto.AccountHistoryResponse;
import com.accountapi.dto.AccountTransactions;
import com.accountapi.exception.InvalidAccountExistsException;
import com.accountapi.repository.AccountBalanceEntity;
import com.accountapi.repository.AccountBalanceRepo;
import com.accountapi.repository.AccountTransactionEntity;
import com.accountapi.repository.AccountTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionHistoryService {

  @Autowired
  AccountTransactionRepo accountTransactionRepo;

  @Autowired
  AccountBalanceRepo accountBalanceRepo;

  int pageSize = 4;

  int firstPage = 0;

  public AccountHistoryResponse getTimedTransactions(AccountHistoryRequest request) throws InvalidAccountExistsException
  {
    AccountHistoryResponse accountHistoryResponse = new AccountHistoryResponse();

    //Find the account to make sure it exists
    AccountBalanceEntity account = accountBalanceRepo.findBy(request.getAccountNumber());
    if(account==null){
      throw new InvalidAccountExistsException();
    }

    //Get Transactions if it Exists
    Page<AccountTransactionEntity> dbEntity = null;

    if(request.getTransType()==null){
      dbEntity = accountTransactionRepo.findAllBy(request.getAccountNumber(), request.getTransStartDate(), request.getTransEndDate(), PageRequest.of(firstPage, pageSize));
    } else {
      dbEntity = accountTransactionRepo.findAllBy(request.getAccountNumber(), request.getTransType(), request.getTransStartDate(), request.getTransEndDate(), PageRequest.of(firstPage, pageSize));
    }

    accountHistoryResponse.setTransCount(String.valueOf(dbEntity.getTotalElements()));
    List<AccountTransactionEntity> accountTransactionList = dbEntity.get().collect(Collectors.toList());

    if(accountTransactionList != null) {

      ArrayList<AccountTransactions> listOfTransactions = new ArrayList<>();
      for(AccountTransactionEntity accountTransactionEntity : accountTransactionList) {
        AccountTransactions accountTransaction = new AccountTransactions();
        accountHistoryResponse.setAccountNumber(accountTransactionEntity.getAccountNumber());
        accountTransaction.setTransAmount(accountTransactionEntity.getAmount());
        accountTransaction.setTransTime(accountTransactionEntity.getTransTimestamp());
        accountTransaction.setTransType(accountTransactionEntity.getTransType());

        listOfTransactions.add(accountTransaction);
      }
      accountHistoryResponse.setAccountTransactions(listOfTransactions);
    }

    APIStatusVO apiStatusVO = new APIStatusVO();
    if(accountTransactionList!=null && accountTransactionList.size()>0 && dbEntity.getTotalElements() > accountTransactionList.size() ){
      apiStatusVO.setMessage("Response was limited to first "+pageSize+" transactions");
      apiStatusVO.setStatus("1");
      //Last transaction is the one we will send back the time for the user to paginate
      accountHistoryResponse.setPaginationTime(accountTransactionList.get(accountTransactionList.size()-1).getTransTimestamp());
    }else if(accountTransactionList!=null && accountTransactionList.size()>0 ){
      apiStatusVO.setMessage("Transactions found and returned successfully");
      apiStatusVO.setStatus("0");
    }else{
      apiStatusVO.setMessage("No Transactions found");
      apiStatusVO.setStatus("3");
    }
    accountHistoryResponse.setStatus(apiStatusVO);

    return accountHistoryResponse;
  }
}
