# accountapi
Account API Assignment



	In this assignment there are two api’s which will return account balance and transactions on an account.
                  1.	Account Balance – 
                  -	API URL (if you are running in local) -- http://localhost:8080/accounts/balance/inquiry 
                  a.	Sample Request
                  {
                  "accountNumber": "454545465"
                  }

                  b.	Sample Response
                  {
                      "accountNumber": "454545465",
                      "accountBalance": 25425.19
                  }

                  2.	Account Transactions –
                  -	API URL (if you are running in local) --  http://localhost:8080/accounts/balance/history  
                  a.	Sample Request
                  {
                  "accountNumber":"454545465",
                  "transStartDate": "2021-01-10T01:30:00.000-05:00",
                  "transEndDate": "2021-01-30T01:30:00.000-05:00"
                  }

                  b.	Sample Response

                  {
                      "status": {
                          "status": "0",
                          "message": "Transactions found and returned successfully"
                      },
                      "accountNumber": "454545465",
                      "transCount": "4",
                      "paginationTime": null,
                      "accountTransactions": [
                          {
                              "transAmount": 445.0,
                              "transTime": "2021-01-16T00:49:58.558+0000",
                              "transType": "Deposit"
                          },
                          {
                              "transAmount": 544.6,
                              "transTime": "2021-01-21T00:49:58.558+0000",
                              "transType": "Withdraw"
                          },
                          {
                              "transAmount": 66.7,
                              "transTime": "2021-01-26T00:49:58.558+0000",
                              "transType": "Withdraw"
                          },
                          {
                              "transAmount": 22.6,
                              "transTime": "2021-01-28T00:49:58.558+0000",
                              "transType": "Withdraw"
                          }
                      ]
                  }


To have better understanding on this account api, please refer the AccountAPI.yaml file.

If you’d like to run this in your local then please use the postman project “AccountService.postman_collection.json”.
