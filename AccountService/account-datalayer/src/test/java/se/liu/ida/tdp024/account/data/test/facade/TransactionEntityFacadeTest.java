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
import se.liu.ida.tdp024.account.data.api.facade.TransactionEntityFacade;
import se.liu.ida.tdp024.account.data.impl.db.facade.TransactionEntityFacadeDB;
import se.liu.ida.tdp024.account.data.api.entity.Transaction;
import se.liu.ida.tdp024.account.data.api.entity.Account;
import se.liu.ida.tdp024.account.data.impl.db.entity.AccountDB;
import se.liu.ida.tdp024.account.data.api.facade.AccountEntityFacade;
import se.liu.ida.tdp024.account.data.impl.db.facade.AccountEntityFacadeDB;

import javax.persistence.EntityManager;
import se.liu.ida.tdp024.account.data.impl.db.util.EMF;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

public class TransactionEntityFacadeTest {

    //---- Unit under test ----//
    private TransactionEntityFacade transactionEntityFacade = new TransactionEntityFacadeDB();
    private AccountEntityFacade accountEntityFacade = new AccountEntityFacadeDB(this.transactionEntityFacade);
    private EntityManager entityManager = EMF.getEntityManager();
    private Account account = new AccountDB();


    @Before
    public void testCreateTransaction() {
        Account account = accountEntityFacade.findByAccountId(1);
        if (account == null) {
            accountEntityFacade.create("CHECK", 1, 1);
            account = accountEntityFacade.findByAccountId(1);
        }
        transactionEntityFacade.create("CHECK", 10, "OK", account);
        transactionEntityFacade.create("CHECK", 10, "OK", null);
    }

    @Test
    public void testFindTransaction() {
        List<Transaction> transactions = transactionEntityFacade.find(this.account);
        assertThat((transactions.size() > 0));
    }
}
