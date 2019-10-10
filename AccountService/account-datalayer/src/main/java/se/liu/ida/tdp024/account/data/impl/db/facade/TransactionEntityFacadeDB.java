package se.liu.ida.tdp024.account.data.impl.db.facade;
import se.liu.ida.tdp024.account.data.impl.db.entity.AccountDB;

import java.util.List;
import java.util.ArrayList;
import java.util.ServiceConfigurationError;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import se.liu.ida.tdp024.account.data.api.entity.Account;
import se.liu.ida.tdp024.account.data.api.entity.Transaction;
import se.liu.ida.tdp024.account.data.impl.db.entity.TransactionDB;
import se.liu.ida.tdp024.account.data.api.facade.TransactionEntityFacade;
import se.liu.ida.tdp024.account.data.impl.db.util.EMF;
import com.google.gson.Gson;

public class TransactionEntityFacadeDB implements TransactionEntityFacade {
    @Override
    public void create(String type, int amount, String status, Account account) {
        // Ifall account är null, inserta inget i databasen
        if (account == null)
            return;

        EntityManager em = EMF.getEntityManager();
        Transaction transaction = new TransactionDB();
        em.getTransaction().begin();
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setStatus(status);
        transaction.setAccount(account);
        em.merge(transaction);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Transaction> find(Account account) {
        EntityManager em = EMF.getEntityManager();
        List<Transaction> result = (List<Transaction>) em.createQuery("SELECT t FROM TransactionDB t WHERE t.account = :account ")
                  .setParameter("account", account)
                  .getResultList();
        em.close();
        return result;
    }
}
