package se.liu.ida.tdp024.account.data.api.entity;
import se.liu.ida.tdp024.account.data.api.entity.Account;


import java.io.Serializable;

public interface Transaction extends Serializable {
    public long getId();
    public String getType();
    public int getAmount();
    public String getDate();
    public String getStatus();
    public Account getAccount();

    public void setId(long id);
    public void setType(String type);
    public void setStatus(String status);
    public void setAccount(Account account);
    public void setAmount(int amount);
}

/*
{
 "id": 144,
 "type": "DEBIT",
 "amount": 8,
 "created": "2013-06-30 14:49:32",
 "status": "OK",
 "account" : {
   "id": 5,
   "personKey": "ahRzfmVudGVycHJpc2Utc3lzdGVtc3INCxIGUGVyc29uGLNtDA",
   "accountType": "CHECK",
   "bankKey": "ahRzfmVudGVycHJpc2Utc3lzdGVtc3ILCxIEQmFuaxiJJww",
   "holdings": 42
 }
*/
