package se.liu.ida.tdp024.account.data.api.facade;

import java.util.List;
import se.liu.ida.tdp024.account.data.api.entity.Transaction;
import se.liu.ida.tdp024.account.data.api.entity.Account;
import javax.persistence.EntityManager;

public interface TransactionEntityFacade {
    public void create(String type, int amount, String status, Account account);
    public void create(String type, int amount, String status, Account account, EntityManager em);
    public List<Transaction> find(Account account);
}
