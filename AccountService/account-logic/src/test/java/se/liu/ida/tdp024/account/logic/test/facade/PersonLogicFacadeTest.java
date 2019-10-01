package se.liu.ida.tdp024.account.logic.test.facade;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.liu.ida.tdp024.account.data.api.util.StorageFacade;
import se.liu.ida.tdp024.account.logic.api.facade.PersonLogicFacade;
import se.liu.ida.tdp024.account.logic.impl.facade.PersonLogicFacadeImpl;
import se.liu.ida.tdp024.account.data.impl.db.facade.PersonEntityFacadeDB;
import javax.persistence.EntityManager;
import se.liu.ida.tdp024.account.data.impl.db.util.EMF;
import java.util.List;
import se.liu.ida.tdp024.account.data.api.entity.Person;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PersonLogicFacadeTest {

    //---- Unit under test ----//
    private PersonLogicFacade personLogicFacade = new PersonLogicFacadeImpl(new PersonEntityFacadeDB());
    private StorageFacade storageFacade;

    private EntityManager entityManager = EMF.getEntityManager();

    @Before
    public void setupTests() {
        personLogicFacade.create("Erik");
    }

    @Test
    public void testList() {
        List<Person> personList = personLogicFacade.list();
        assertThat((personList.size() > 0));
    }

    @Test
    public void testFindByName() {
        String name = "Erik";
        List<Person> personList = personLogicFacade.find(name);
        System.out.println(personList.size());
        assertTrue("Personlist should be bigger than 0", personList.size() > 0);
        assertThat(personList.get(0).getName()).isEqualTo("Erik");
    }

    @Test
    public void testFindById() {
        long id = 1;
        Person person = personLogicFacade.find(id);
        assertThat(person).isNotEqualTo(null);
    }


    @After
    public void tearDown() {
      if (storageFacade != null)
        storageFacade.emptyStorage();
    }

}
