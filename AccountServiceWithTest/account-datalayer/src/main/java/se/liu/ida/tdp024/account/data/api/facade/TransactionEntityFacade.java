package se.liu.ida.tdp024.account.data.api.facade;

import java.util.List;
import se.liu.ida.tdp024.account.data.api.entity.Transaction;
import se.liu.ida.tdp024.account.data.api.entity.Account;

public interface TransactionEntityFacade {
    public void create(String type, int amount, String status, Account account);
    public List<Transaction> find(Account account);
}
/*

public interface PersonEntityFacade {

    public List<Person> list();

    public List<Person> find(String name);

    public Person find(long key);

    public long create(String name);

    //public List<Todo> findAll();
}
*/
