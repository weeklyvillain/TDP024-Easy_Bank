package se.liu.ida.tdp024.bank.logic.test.facade;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.liu.ida.tdp024.bank.data.api.util.StorageFacade;
import se.liu.ida.tdp024.bank.logic.api.facade.BankLogicFacade;
import se.liu.ida.tdp024.bank.logic.impl.facade.BankLogicFacadeImpl;
import se.liu.ida.tdp024.bank.data.impl.db.facade.BankEntityFacadeDB;
import javax.persistence.EntityManager;
import se.liu.ida.tdp024.bank.data.impl.db.util.EMF;
import java.util.List;
import se.liu.ida.tdp024.bank.data.api.entity.Bank;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BankLogicFacadeTest {
    //---- Unit under test ----//
    private BankLogicFacade bankLogicFacade = new BankLogicFacadeImpl(new BankEntityFacadeDB());
    private StorageFacade storageFacade;

    private EntityManager entityManager = EMF.getEntityManager();

    private String bankName = "Handlesbanken";

    @Before
    public void setupTests() {
        bankLogicFacade.create(bankName);
    }

    @Test
    public void testList() {
        List<Bank> bankList = bankLogicFacade.list();
        assertThat((bankList.size() > 0));
    }

    @Test
    public void testFindByName() {
        Bank bank = bankLogicFacade.find(bankName);
        assertThat(bank.getName()).isEqualTo(bankName);
    }

    @Test
    public void testFindById() {
        long id = 1;
        Bank bank = bankLogicFacade.find(id);
        assertThat(bank).isNotEqualTo(null);
    }


    @After
    public void tearDown() {
      if (storageFacade != null)
        storageFacade.emptyStorage();
    }

}
