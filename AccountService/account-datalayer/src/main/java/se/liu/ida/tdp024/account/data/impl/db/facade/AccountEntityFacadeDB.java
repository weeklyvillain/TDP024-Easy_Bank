package se.liu.ida.tdp024.account.data.impl.db.facade;

import java.util.List;
import java.util.ArrayList;
import java.util.ServiceConfigurationError;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import se.liu.ida.tdp024.account.data.api.entity.Account;
import se.liu.ida.tdp024.account.data.impl.db.entity.AccountDB;
import se.liu.ida.tdp024.account.data.api.facade.AccountEntityFacade;
import se.liu.ida.tdp024.account.data.impl.db.util.EMF;
import se.liu.ida.tdp024.account.data.impl.db.facade.TransactionEntityFacadeDB;
import se.liu.ida.tdp024.account.data.api.facade.TransactionEntityFacade;


public class AccountEntityFacadeDB implements AccountEntityFacade {
    TransactionEntityFacade transactionEntityFacade;

    public AccountEntityFacadeDB(TransactionEntityFacade transactionEntityFacade) {
        this.transactionEntityFacade = transactionEntityFacade;
    }


    @Override
    public boolean create(String type, long personKey, long bankKey) {
        EntityManager em = EMF.getEntityManager();
        Account account = new AccountDB();
        em.getTransaction().begin();
        account.setType(type);
        account.setBankKey(bankKey);
        account.setPersonKey(personKey);
        account.setHoldings(0);
        em.persist(account);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public List<Account> findByPersonKey(long personKey) {
        EntityManager em = EMF.getEntityManager();
        List<Account> result = (List<Account>) em.createQuery("SELECT t FROM AccountDB t WHERE t.personKey = :key ")
              .setParameter("key", personKey)
              .getResultList();
        em.close();
        return result;
    }

    @Override
    public boolean debit(long id, int amount) {
        String type = "DEBIT";
        String status = "";
        boolean result;

        EntityManager em = EMF.getEntityManager();
        Account account = this.findByAccountId(id, em);
        try {
            em.getTransaction().begin();
            em.lock(account, LockModeType.PESSIMISTIC_WRITE);

            if (account.getHoldings() < amount || amount < 0) {
                status = "FAILED";
                result = false;
            } else {
                account.setHoldings(account.getHoldings() - amount);
                em.merge(account);
                status = "OK";
                result = true;
            }
            this.transactionEntityFacade.create(type, amount, status, account, em);
            em.getTransaction().commit();
            return result;
        } catch(Exception e) {
          System.out.println(e);
          return false;
        } finally {
          if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
          }
          em.close();
        }
    }

    @Override
    public boolean credit(long id, int amount) {
        String type = "CREDIT";
        String status = "";
        boolean result;

        EntityManager em = EMF.getEntityManager();
        Account account = this.findByAccountId(id, em);
        System.out.println(account);

        try {
            em.getTransaction().begin();
            em.lock(account, LockModeType.PESSIMISTIC_WRITE);

            if (amount < 0) {
                status = "FAILED";
                result = false;
            } else {
                account.setHoldings(account.getHoldings() + amount);
                em.merge(account);
                status = "OK";
                result = true;
            }
            this.transactionEntityFacade.create(type, amount, status, account, em);
            em.getTransaction().commit();
            return result;
        } catch(Exception e) {
          System.out.println(e);
          return false;
        } finally {
          if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
          }
          em.close();
        }
    }


    // Help functions
    @Override
    public Account findByAccountId(long id, EntityManager em) {
        System.out.println("ID: " + id);
        try {
            Account result = (Account) em.createQuery("SELECT t FROM AccountDB t WHERE t.id = :id ")
                  .setParameter("id", id)
                  .getSingleResult();
            return result;
        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
    @Override
    public Account findByAccountId(long id) {
        EntityManager em = EMF.getEntityManager();
        try {
            Account result = (Account) em.createQuery("SELECT t FROM AccountDB t WHERE t.id = :id ")
                  .setParameter("id", id)
                  .getSingleResult();
            return result;
        } catch(Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
