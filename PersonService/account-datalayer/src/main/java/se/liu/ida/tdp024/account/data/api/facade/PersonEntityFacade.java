package se.liu.ida.tdp024.account.data.api.facade;

import java.util.List;
import se.liu.ida.tdp024.account.data.api.entity.Person;

public interface PersonEntityFacade {

    public List<Person> list();

    public List<Person> find(String name);

    public Person find(long key);
}
