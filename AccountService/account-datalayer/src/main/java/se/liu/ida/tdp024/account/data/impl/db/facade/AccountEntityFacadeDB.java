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

public class AccountEntityFacadeDB implements AccountEntityFacade {

    @Override
    public boolean create(String type, long personKey, long bankKey) {
        EntityManager em = EMF.getEntityManager();
        Account account = new AccountDB();
        try {
        em.getTransaction().begin();
        account.setType(type);
        account.setBankKey(bankKey);
        account.setPersonKey(personKey);
        account.setHoldings(0);
          em.persist(account);
          em.getTransaction().commit();
          return true;
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
    public List<Account> findByPersonKey(long personKey) {
        EntityManager em = EMF.getEntityManager();
        try {
            List<Account> result = (List<Account>) em.createQuery("SELECT t FROM AccountDB t WHERE t.personKey = :key ")
                  .setParameter("key", personKey)
                  .getResultList();
            return result;
        } catch(Exception e) {
            System.out.println(e);
            return new ArrayList<Account>();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean debit(long id, int amount) {
        EntityManager em = EMF.getEntityManager();
        Account account = this.findByAccountId(id, em);
        try {
            em.getTransaction().begin();
            em.lock(account, LockModeType.PESSIMISTIC_WRITE);
            if (account.getHoldings() < amount) {
                return false;
            }
            account.setHoldings(account.getHoldings() - amount);
            em.merge(account);
            em.getTransaction().commit();
            return true;
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
        EntityManager em = EMF.getEntityManager();
        Account account = this.findByAccountId(id, em);

        try {
            em.getTransaction().begin();
            em.lock(account, LockModeType.PESSIMISTIC_WRITE);
            account.setHoldings(account.getHoldings() + amount);
            em.merge(account);
            em.getTransaction().commit();
            return true;
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
        try {
            Account result = (Account) em.createQuery("SELECT t FROM AccountDB t WHERE t.id = :id ")
                  .setParameter("id", id)
                  .getSingleResult();
            return result;
        } catch(Exception e) {
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

/*
public class AccountEntityFacadeDB implements AccountEntityFacade {

    @Override
    public List<Account> list() {
      EntityManager em = EMF.getEntityManager();

      Query query = em.createQuery("SELECT t FROM AccountDB t");
      List<Account> result =  query.getResultList();
      em.close();
      return result;
    }

    @Override
    public List<Account> find(String name) {
        EntityManager em = EMF.getEntityManager();
        List<Account> result = (List<Account>) em.createQuery("SELECT t FROM AccountDB t WHERE t.name LIKE :name ")
              .setParameter("name", name)
              .getResultList();
        em.close();
        return result;
    }

    @Override
    public Account find(long key) {
        EntityManager em = EMF.getEntityManager();
        try {
            Account result = (Account) em.createQuery("SELECT t FROM AccountDB t WHERE t.id = :key ")
                  .setParameter("key", key)
                  .getSingleResult();
            return result;
        } catch(Exception e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        }

    }

    @Override
    public long create(String name) {
      EntityManager em = EMF.getEntityManager();

      try {
        em.getTransaction().begin();
        Account person = new AccountDB();
        person.setName(name);
        em.persist(person);
        em.getTransaction().commit();
        return person.getId();
      } catch(Exception e) {
        System.out.println(e);
        return -1;
      } finally {
        if (em.getTransaction().isActive()) {
          em.getTransaction().rollback();
        }
        em.close();
      }
    }


    /*
    @Override
    public long create(String title, String body) {

        EntityManager em = EMF.getEntityManager();

        try {

            em.getTransaction().begin();

            Todo todo = new TodoDB();
            todo.setTitle(title);
            todo.setBody(body);

            em.persist(todo);

            em.getTransaction().commit();

            return todo.getId();

        } catch (Exception e) {

            todoLogger.log(e);
            throw new ServiceConfigurationError("Commit fails");

        } finally {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

    }

    @Override
    public void setActive(long id, boolean active) {

        EntityManager em = EMF.getEntityManager();

        try {

            em.getTransaction().begin();

            Todo todo = em.find(TodoDB.class, id, LockModeType.PESSIMISTIC_WRITE);
            todo.setActive(active);

            em.merge(todo);

            em.getTransaction().commit();

        } catch (Exception e) {

            todoLogger.log(e);

        } finally {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            em.close();
        }

    }

    @Override
    public Todo find(long id) {

        EntityManager em = EMF.getEntityManager();

        try {

            return em.find(TodoDB.class, id);

        } catch (Exception e) {

            todoLogger.log(e);
            return null;

        } finally {
            em.close();
        }

    }

    @Override
    public List<Todo> findAll() {

        EntityManager em = EMF.getEntityManager();

        try {

            Query query = em.createQuery("SELECT t FROM TodoDB t");
            return query.getResultList();

        } catch (Exception e) {

            todoLogger.log(e);
            return null;

        } finally {
            em.close();
        }

    }
}
*/
