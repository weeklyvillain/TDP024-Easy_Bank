package se.liu.ida.tdp024.bank.data.impl.db.facade;

import java.util.List;
import java.util.ServiceConfigurationError;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import se.liu.ida.tdp024.bank.data.api.entity.Bank;
import se.liu.ida.tdp024.bank.data.impl.db.entity.BankDB;
import se.liu.ida.tdp024.bank.data.api.facade.BankEntityFacade;
import se.liu.ida.tdp024.bank.data.impl.db.util.EMF;

public class BankEntityFacadeDB implements BankEntityFacade {

    @Override
    public List<Bank> list() {
      EntityManager em = EMF.getEntityManager();

      Query query = em.createQuery("SELECT t FROM BankDB t");
      List<Bank> result =  query.getResultList();
      em.close();
      return result;
    }

    @Override
    public Bank find(String name) {
        EntityManager em = EMF.getEntityManager();

        try {
            Bank result = (Bank) em.createQuery("SELECT t FROM BankDB t WHERE t.name LIKE :name ")
                  .setParameter("name", name)
                  .getSingleResult();
              return result;
          } catch(Exception e) {
              //System.out.println(e);
              return null;
          } finally {
              em.close();
          }
    }

    @Override
    public Bank find(long key) {
        EntityManager em = EMF.getEntityManager();
        try {
            Bank result = (Bank) em.createQuery("SELECT t FROM BankDB t WHERE t.id = :key ")
                  .setParameter("key", key)
                  .getSingleResult();
            return result;
        } catch(Exception e) {
            //System.out.println(e);
            return null;
        } finally {
            em.close();
        }

    }

    public static long create(String name) {
      EntityManager em = EMF.getEntityManager();
      try {
        em.getTransaction().begin();
        Bank bank = new BankDB();
        bank.setName(name);
        em.persist(bank);
        em.getTransaction().commit();
        return bank.getId();
      } catch(Exception e) {
        //System.out.println(e);
        return -1;
      } finally {
        if (em.getTransaction().isActive()) {
          em.getTransaction().rollback();
        }
        em.close();
      }
    }
}
