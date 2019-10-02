package se.liu.ida.tdp024.account.data.impl.db.facade;

import java.util.List;
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
        /*
        EntityManager em = EMF.getEntityManager();

        try {
          em.getTransaction().begin();
          Account account = new AccountDB();
          account.setName(name);
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
        */
        return true;
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
