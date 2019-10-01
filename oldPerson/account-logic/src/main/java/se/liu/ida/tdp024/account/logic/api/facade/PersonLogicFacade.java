package se.liu.ida.tdp024.account.logic.api.facade;

import se.liu.ida.tdp024.account.data.api.entity.Person;
import java.util.List;

public interface PersonLogicFacade {

    //public long create(String title, String body);

    public List<Person> list();

    public List<Person> find(String name);

    public Person find(long key);

    public long create(String name);

    //public Todo find(long id);

    //public void checkOut(long id);

    //public void checkIn(long id);
}
