package se.liu.ida.tdp024.bank.data.api.facade;

import java.util.List;
import se.liu.ida.tdp024.bank.data.api.entity.Bank;

public interface BankEntityFacade {

    public List<Bank> list();

    public Bank find(String name);

    public Bank find(long key);

}
