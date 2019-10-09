package se.liu.ida.tdp024.account.logic.api.facade;

import se.liu.ida.tdp024.account.data.api.entity.Account;
import se.liu.ida.tdp024.account.data.api.entity.Transaction;
import java.util.List;

/*
public interface AccountLogicFacade {

    //public long create(String title, String body);

    public List<Account> list();

    public List<Account> find(String name);

    public Account find(long key);

    public long create(String name);

    //public Todo find(long id);

    //public void checkOut(long id);

    //public void checkIn(long id);
}
*/


public interface AccountLogicFacade {
    public boolean create(String type, long personKey, String bankName);
    public List<Account> find(long personKey);
    public boolean debit(long accountId, int amount);
    public boolean credit(long accountId, int amount);
    public List<Transaction> getTransactions(long accountId);
}
