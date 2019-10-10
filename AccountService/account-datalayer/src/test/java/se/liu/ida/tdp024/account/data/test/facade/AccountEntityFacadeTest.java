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
import se.liu.ida.tdp024.account.data.api.facade.AccountEntityFacade;
import se.liu.ida.tdp024.account.data.impl.db.facade.AccountEntityFacadeDB;
import se.liu.ida.tdp024.account.data.api.entity.Account;

import javax.persistence.EntityManager;
import se.liu.ida.tdp024.account.data.impl.db.util.EMF;
import org.junit.Before;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class AccountEntityFacadeTest {

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

    @Before
    public void AccountEntityFacadeTest() {
        boolean result = accountEntityFacade.create("CHECK", 1, 1);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void testFindPersonByKey() {
        List<Account> result = accountEntityFacade.findByPersonKey(1);
        assertThat((result.size() > 0));
    }

    private void testCredit() {
        boolean result = accountEntityFacade.credit(1, 50);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void testDebitCredit() {
        boolean failResult = accountEntityFacade.debit(1, 100000);
        assertThat(failResult).isEqualTo(false);
        testCredit();
        boolean result = accountEntityFacade.debit(1, 50);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void testFindByAccountId() {
        Account account = accountEntityFacade.findByAccountId(-1, this.entityManager);
        assertThat(account).isEqualTo(null);
        account = accountEntityFacade.findByAccountId(1, this.entityManager);
        assertThat(account).isNotEqualTo(null);

        account = accountEntityFacade.findByAccountId(-1);
        assertThat(account).isEqualTo(null);
        account = accountEntityFacade.findByAccountId(1);
        assertThat(account).isNotEqualTo(null);
    }
}
