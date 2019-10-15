package se.liu.ida.tdp024.account.logic.test.facade;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.liu.ida.tdp024.account.data.api.util.StorageFacade;
import javax.persistence.EntityManager;
import se.liu.ida.tdp024.account.data.impl.db.util.EMF;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import se.liu.ida.tdp024.account.logic.api.facade.AccountLogicFacade;
import se.liu.ida.tdp024.account.logic.impl.facade.AccountLogicFacadeImpl;

import se.liu.ida.tdp024.account.data.impl.db.facade.AccountEntityFacadeDB;
import se.liu.ida.tdp024.account.data.impl.db.facade.TransactionEntityFacadeDB;

import se.liu.ida.tdp024.account.data.api.facade.AccountEntityFacade;
import se.liu.ida.tdp024.account.data.api.facade.TransactionEntityFacade;

import se.liu.ida.tdp024.account.util.http.HTTPHelperImpl;

import se.liu.ida.tdp024.account.data.api.entity.Account;
import se.liu.ida.tdp024.account.data.api.entity.Transaction;


public class AccountLogicFacadeTest {
    private AccountLogicFacade accountLogicFacade = new AccountLogicFacadeImpl(new AccountEntityFacadeDB(new TransactionEntityFacadeDB()),
                                                                               new TransactionEntityFacadeDB(),
                                                                               new HTTPHelperImpl());
    private String BANK_NAME = "HANDELSBANKEN";
    @Before
    public void Setup() {
        accountLogicFacade.create("CHECK", 1, this.BANK_NAME);
        accountLogicFacade.debit(1, 50);
    }

    @Test
    public void testCreate() {
        boolean result = accountLogicFacade.create("CHECK", 1, this.BANK_NAME);
        assertThat(result).isEqualTo(true);
        result = accountLogicFacade.create("SAVINGS", 1, this.BANK_NAME);
        assertThat(result).isEqualTo(true);
        result = accountLogicFacade.create("CS:GO", 1, this.BANK_NAME);
        assertThat(result).isEqualTo(false);
        result = accountLogicFacade.create("CHECK", -1, this.BANK_NAME);
        assertThat(result).isEqualTo(false);
        result = accountLogicFacade.create("CHECK", 1, "BANK_MCBANK_FACE");
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void testFind() {
        List<Account> accounts = accountLogicFacade.find(1);
        assertThat((accounts.size() > 0));
    }

    @Test
    public void testDebitCredit() {
        boolean result = accountLogicFacade.debit(100, 100000);
        assertThat(result).isEqualTo(false);

        result = accountLogicFacade.credit(100, 100000);
        assertThat(result).isEqualTo(false);

        result = accountLogicFacade.debit(1, 100000);
        assertThat(result).isEqualTo(false);

        result = accountLogicFacade.debit(1, -100);
        assertThat(result).isEqualTo(false);

        result = accountLogicFacade.credit(1, -100);
        assertThat(result).isEqualTo(false);

        result = accountLogicFacade.credit(1, 100);
        assertThat(result).isEqualTo(true);

        result = accountLogicFacade.debit(1, 50);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void testGetTransactions() {
        List<Transaction> transactions = accountLogicFacade.getTransactions(1);
        assertThat((transactions.size() > 0));
    }

}
