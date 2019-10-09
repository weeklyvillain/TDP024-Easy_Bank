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
        EntityManager em = EMF.getEntityManager();
        Transaction transaction = new TransactionDB();

        try {
          em.getTransaction().begin();
          transaction.setType(type);
          transaction.setAmount(amount);
          transaction.setStatus(status);
          transaction.setAccount(account);
          em.merge(transaction);
          em.getTransaction().commit();
        } catch(Exception e) {
          System.out.println(e);
        } finally {
          if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
          }
          em.close();
        }
    }

    @Override
    public List<Transaction> find(Account account) {
        EntityManager em = EMF.getEntityManager();
        try {
            List<Transaction> result = (List<Transaction>) em.createQuery("SELECT t FROM TransactionDB t WHERE t.account = :account ")
                  .setParameter("account", account)
                  .getResultList();
            return result;
        } catch(Exception e) {
            System.out.println(e);
            return new ArrayList<Transaction>();
        } finally {
            em.close();
        }
    }
}
