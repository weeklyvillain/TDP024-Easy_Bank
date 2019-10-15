package se.liu.ida.tdp024.bank.data.test.facade;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.liu.ida.tdp024.bank.data.api.facade.BankEntityFacade;
import se.liu.ida.tdp024.bank.data.impl.db.facade.BankEntityFacadeDB;
import se.liu.ida.tdp024.bank.data.api.util.StorageFacade;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import se.liu.ida.tdp024.bank.data.api.entity.Bank;
import se.liu.ida.tdp024.bank.data.impl.db.entity.BankDB;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import se.liu.ida.tdp024.bank.data.impl.db.util.EMF;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class BankEntityFacadeTest {

    //---- Unit under test ----//
    private BankEntityFacade bankEntityFacade = new BankEntityFacadeDB();
    private StorageFacade storageFacade;

    private EntityManager entityManager = EMF.getEntityManager();

    private String bankName = "Handelsbanken";

    @Before
    public void createBank() {
        BankEntityFacadeDB.create(bankName);
    }

    @Test
    public void testCreateBankWithSameName() {
        long id = BankEntityFacadeDB.create(bankName);
        assertThat(id).isEqualTo(-1);
    }

    @Test
    public void testFindBankByName() {
        Bank bank = bankEntityFacade.find(bankName);
        assertThat(bank.getName()).isEqualTo(bankName);
    }

    @Test
    public void testFindBankByNonExistingName() {
        Bank bank = bankEntityFacade.find("finns inte");
        assertThat(bank).isEqualTo(null);
    }

    @Test
    public void testFindBankById() {
        Bank bank = bankEntityFacade.find(1);
        assertThat(bank).isNotEqualTo(null);
    }

    @Test
    public void testFindBankByNonExistingId() {
        Bank bank = bankEntityFacade.find(0);
        assertThat(bank).isEqualTo(null);
    }

    @Test
    public void testList() {
        List<Bank> bankList = bankEntityFacade.list();
        assertThat(bankList).isNotEqualTo(null);
    }

    @After
    public void tearDown() {
        this.entityManager.close();
    }


}
