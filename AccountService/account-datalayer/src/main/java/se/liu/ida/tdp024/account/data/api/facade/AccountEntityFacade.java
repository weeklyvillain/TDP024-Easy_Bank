package se.liu.ida.tdp024.account.data.api.facade;

import java.util.List;
import se.liu.ida.tdp024.account.data.api.entity.Account;
import javax.persistence.EntityManager;

public interface AccountEntityFacade {
    public boolean create(String type, long personKey, long bankKey);
    public List<Account> findByPersonKey(long personKey);
    public boolean debit(long id, int amount);
    public boolean credit(long id, int amount);


    // Help functions
    public Account findByAccountId(long id, EntityManager em);
    public Account findByAccountId(long id);
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
