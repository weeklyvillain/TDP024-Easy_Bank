package se.liu.ida.tdp024.bank.logic.api.facade;

import se.liu.ida.tdp024.bank.data.api.entity.Bank;
import java.util.List;

public interface BankLogicFacade {

    //public long create(String title, String body);

    public List<Bank> list();

    public Bank find(String name);

    public Bank find(long key);

    public long create(String name);

    //public Todo find(long id);

    //public void checkOut(long id);

    //public void checkIn(long id);
}
