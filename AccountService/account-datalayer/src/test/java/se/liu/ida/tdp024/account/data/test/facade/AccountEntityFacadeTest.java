package se.liu.ida.tdp024.account.data.test.facade;

import java.util.List;
import org.junit.After;
import org.junit.Test;
import se.liu.ida.tdp024.account.data.api.util.StorageFacade;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import se.liu.ida.tdp024.account.data.impl.db.util.EMF;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class PersonEntityFacadeTest {

    //---- Unit under test ----//
    private AccountEntityFacade accountEntityFacade = new AccountEntityFacadeDB();
    private EntityManager entityManager = EMF.getEntityManager();
    /*

    public boolean create(String type, long personKey, long bankKey);
    public List<Account> findByPersonKey(long personKey);
    public boolean debit(long id, int amount);
    public boolean credit(long id, int amount);


    // Help functions
    public Account findByAccountId(long id, EntityManager em);
    public Account findByAccountId(long id);
    */

    @Test
    public void testCreateAccount() {
        boolean result = accountEntityFacade.create("CHECK", 1, 1);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void testFindPersonByKey() {
        List<Account> result = accountEntityFacade.findByPersonKey(1);
    }
}
