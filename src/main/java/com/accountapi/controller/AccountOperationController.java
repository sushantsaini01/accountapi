package com.accountapi.controller;

import com.accountapi.repository.AccountBalanceRepo;
import com.accountapi.service.TransactionHistoryService;
import com.accountapi.dto.*;
import com.accountapi.exception.InvalidAccountExistsException;
import com.accountapi.repository.AccountBalanceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController()
public class AccountOperationController {

  @Autowired
  private AccountBalanceRepo accountBalanceRepo;

  @Autowired
  TransactionHistoryService transactionHistoryService;

  @PostMapping("/accounts/balance/inquiry")
  public ResponseEntity getAccountLatestBalance(@RequestHeader(name="client_key") String clientKey, @RequestHeader(name="transaction_id") String transactionid,@Valid @RequestBody
    AccountBalanceRequest accountBalanceRequest){
    AccountBalanceResponse accountBalanceResponse = new AccountBalanceResponse();
    AccountBalanceEntity accountBalanceEntity = null;
    ResponseEntity responseEntity = null;
    accountBalanceEntity = accountBalanceRepo.findBy(accountBalanceRequest.getAccountNumber());
    if (accountBalanceEntity == null) {
      responseEntity =  ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body("{\"status\":\"111\",\"description\":\"account not found.\"}");
    }
    else {
      accountBalanceResponse.setAccountNumber(accountBalanceEntity.getAccountNumber());
      accountBalanceResponse.setAccountBalance(accountBalanceEntity.getBalance());
      responseEntity = ResponseEntity.ok().body(accountBalanceResponse);
    }
    return responseEntity;
  }

  @PostMapping("/accounts/balance/history")
  @ResponseBody
  public ResponseEntity getAccountHistory(@RequestHeader(name="client_key") String clientKey, @RequestHeader(name="transaction_id") String transactionID, @Valid @RequestBody
    AccountHistoryRequest accountHistoryReq) {

    ResponseEntity responseEntity=null;

    AccountHistoryResponse accountHistoryResponse = null;
    try {
      accountHistoryResponse = transactionHistoryService.getTimedTransactions(accountHistoryReq);
      responseEntity = ResponseEntity.ok().body(accountHistoryResponse);
    }catch(InvalidAccountExistsException e){
      accountHistoryResponse = new AccountHistoryResponse();
      APIStatusVO apiStatusVO = new APIStatusVO();
      apiStatusVO.setMessage("Account Not found");
      apiStatusVO.setStatus("2");
      accountHistoryResponse.setStatus(apiStatusVO);
      responseEntity = ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(accountHistoryResponse);
    }
    return responseEntity;
  }


}
