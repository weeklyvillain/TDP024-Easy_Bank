package se.liu.ida.tdp024.bank.logic.impl.facade;

import se.liu.ida.tdp024.bank.data.api.entity.Bank;
import se.liu.ida.tdp024.bank.data.api.facade.BankEntityFacade;
import se.liu.ida.tdp024.bank.logic.api.facade.BankLogicFacade;
import java.util.List;

public class BankLogicFacadeImpl implements BankLogicFacade {

    private BankEntityFacade bankEntityFacade;

    public BankLogicFacadeImpl(BankEntityFacade bankEntityFacade) {
        this.bankEntityFacade = bankEntityFacade;
    }

    @Override
    public List<Bank> list() {
      return bankEntityFacade.list();
    }

    @Override
    public Bank find(String name) {
      return bankEntityFacade.find(name);
    }

    @Override
    public Bank find(long key) {
      return bankEntityFacade.find(key);
    }
}
