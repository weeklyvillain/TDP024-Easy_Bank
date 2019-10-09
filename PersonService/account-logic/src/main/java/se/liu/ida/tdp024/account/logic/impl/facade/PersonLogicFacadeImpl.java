package se.liu.ida.tdp024.account.logic.impl.facade;

import se.liu.ida.tdp024.account.data.api.entity.Person;
import se.liu.ida.tdp024.account.data.api.facade.PersonEntityFacade;
import se.liu.ida.tdp024.account.logic.api.facade.PersonLogicFacade;
import java.util.List;

public class PersonLogicFacadeImpl implements PersonLogicFacade {

    //private TodoEntityFacade todoEntityFacade;
    private PersonEntityFacade personEntityFacade;

    public PersonLogicFacadeImpl(PersonEntityFacade personEntityFacade) {
        this.personEntityFacade = personEntityFacade;
    }

    @Override
    public List<Person> list() {
      return personEntityFacade.list();
    }

    @Override
    public List<Person> find(String name) {
      return personEntityFacade.find(name);
    }

    @Override
    public Person find(long key) {
      return personEntityFacade.find(key);
    }


    /*
    @Override
    public long create(String title, String body) {
        return todoEntityFacade.create(title, body);
    }

    @Override
    public Todo find(long id) {
        return todoEntityFacade.find(id);
    }

    @Override
    public void checkOut(long id) {
        this.todoEntityFacade.setActive(id, true);
    }

    @Override
    public void checkIn(long id) {
        this.todoEntityFacade.setActive(id, false);
    }
    */
}
