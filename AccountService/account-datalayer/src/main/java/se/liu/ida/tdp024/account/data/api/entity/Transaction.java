package se.liu.ida.tdp024.account.data.api.entity;
import se.liu.ida.tdp024.account.data.api.entity.Account;


import java.io.Serializable;

public interface Transaction extends Serializable {

    public void setType(String type);
    public void setStatus(String status);
    public void setAccount(Account account);
    public void setAmount(int amount);
}
