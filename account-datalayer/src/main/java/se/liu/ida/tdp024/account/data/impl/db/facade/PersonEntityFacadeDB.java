package se.liu.ida.tdp024.account.data.impl.db.facade;

import java.util.List;
import java.util.ServiceConfigurationError;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import se.liu.ida.tdp024.account.data.api.entity.Person;
import se.liu.ida.tdp024.account.data.impl.db.entity.PersonDB;
import se.liu.ida.tdp024.account.data.api.facade.PersonEntityFacade;
import se.liu.ida.tdp024.account.data.impl.db.util.EMF;

public class PersonEntityFacadeDB implements PersonEntityFacade {

    @Override
    public List<Person> list() {
      EntityManager em = EMF.getEntityManager();

      try {
          Query query = em.createQuery("SELECT t FROM PersonDB t");
          return query.getResultList();
      } catch (Exception e) {
          System.out.println(e);
          return null;
      } finally {
          em.close();
      }
    }

    @Override
    public List<Person> find(String name) {
        EntityManager em = EMF.getEntityManager();
        try {
            return (List<Person>) em.createQuery("SELECT t FROM PersonDB t WHERE t.name LIKE :name ")
                  .setParameter("name", name)
                  .getResultList();
        } catch(Exception e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Person find(long key) {
        EntityManager em = EMF.getEntityManager();
        try {
            return (Person) em.createQuery("SELECT t FROM PersonDB t WHERE t.id = :key ")
                  .setParameter("key", key)
                  .getSingleResult();
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
        Person person = new PersonDB();
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
    */
}
