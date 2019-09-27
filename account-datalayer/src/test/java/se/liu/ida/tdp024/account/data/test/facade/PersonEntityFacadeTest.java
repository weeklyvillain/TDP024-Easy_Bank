package se.liu.ida.tdp024.account.data.test.facade;

import java.util.List;
import org.junit.After;
import org.junit.Test;
import se.liu.ida.tdp024.account.data.api.facade.PersonEntityFacade;
import se.liu.ida.tdp024.account.data.impl.db.facade.PersonEntityFacadeDB;
import se.liu.ida.tdp024.account.data.api.util.StorageFacade;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import se.liu.ida.tdp024.account.data.api.entity.Person;
import se.liu.ida.tdp024.account.data.impl.db.entity.PersonDB;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import se.liu.ida.tdp024.account.data.impl.db.util.EMF;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class PersonEntityFacadeTest {

    //---- Unit under test ----//
    private PersonEntityFacade personEntityFacade = new PersonEntityFacadeDB();
    private StorageFacade storageFacade;

    private EntityManager entityManager = EMF.getEntityManager();
    /*

    public List<Person> list();

    public List<Person> find(String name);

    public Person find(long key);

    public long create(String name);
    */

    @Test
    public void testCreatePerson() {
        long id = personEntityFacade.create("Erik");
        assertThat(id).isNotEqualTo(-1);
    }

    @Test
    public void testFindPersonByName() {
        List<Person> personList = personEntityFacade.find("Erik");
        assertThat((personList.size() > 0));
        assertThat(personList.get(0).getName()).isEqualTo("Erik");
    }

    @Test
    public void testFindPersonById() {
        Person person = personEntityFacade.find(1);
        assertThat(person).isNotEqualTo(null);
    }

    @Test
    public void testList() {
        List<Person> personList = personEntityFacade.list();
        assertThat(personList).isNotEqualTo(null);
    }

    @After
    public void tearDown() {
        this.entityManager.close();
       // storageFacade.emptyStorage();
    }

    @Test
    public void testCreate() {
    }
}
