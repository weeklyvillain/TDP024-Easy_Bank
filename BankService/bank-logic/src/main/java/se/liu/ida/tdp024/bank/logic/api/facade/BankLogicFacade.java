package se.liu.ida.tdp024.bank.logic.api.facade;

import se.liu.ida.tdp024.bank.data.api.entity.Bank;
import java.util.List;

public interface BankLogicFacade {

    public List<Bank> list();

    public Bank find(String name);

    public Bank find(long key);
}
