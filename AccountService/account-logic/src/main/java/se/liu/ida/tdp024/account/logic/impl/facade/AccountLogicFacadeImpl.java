package se.liu.ida.tdp024.account.logic.impl.facade;

import se.liu.ida.tdp024.account.data.api.entity.Account;
import se.liu.ida.tdp024.account.data.api.entity.Transaction;
import se.liu.ida.tdp024.account.data.api.facade.AccountEntityFacade;
import se.liu.ida.tdp024.account.data.api.facade.TransactionEntityFacade;
import se.liu.ida.tdp024.account.logic.api.facade.AccountLogicFacade;
import java.util.List;
import se.liu.ida.tdp024.account.util.http.HTTPHelper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

public class AccountLogicFacadeImpl implements AccountLogicFacade {

    private AccountEntityFacade accountEntityFacade;
    private TransactionEntityFacade transactionEntityFacade;
    private HTTPHelper httpHelper;

    public AccountLogicFacadeImpl(AccountEntityFacade accountEntityFacade, TransactionEntityFacade transactionEntityFacade, HTTPHelper httpHelper) {
        this.accountEntityFacade = accountEntityFacade;
        this.transactionEntityFacade = transactionEntityFacade;
        this.httpHelper = httpHelper;
    }

    @Override
    public boolean create(String type, long personKey, String bankName) {
        if (!validateType(type) || !validatePersonKey(personKey) || !validateBankName(bankName)) {
            return false;
        }

        long bankKey = getBankKey(bankName);
        return this.accountEntityFacade.create(type, personKey, bankKey);
    }

    @Override
    public List<Account> find(long personKey) {
        return this.accountEntityFacade.findByPersonKey(personKey);
    }

    @Override
    public boolean debit(long accountId, int amount) {
        Account account = this.accountEntityFacade.findByAccountId(accountId);
        boolean result = false;
        if (account != null && account.getHoldings() >= amount && amount > 0) {
            result = this.accountEntityFacade.debit(accountId, amount);
        }
        String status = result ? "OK" : "FAILED";
        this.transactionEntityFacade.create("DEBIT", amount, status, account);
        return result;
    }

    @Override
    public boolean credit(long accountId, int amount) {
        Account account = this.accountEntityFacade.findByAccountId(accountId);
        boolean result = false;
        if (account != null && account.getHoldings() >= amount && amount > 0) {
            result = this.accountEntityFacade.credit(accountId, amount);
        }
        String status = result ? "OK" : "FAILED";
        this.transactionEntityFacade.create("CREDIT", amount, status, account);
        return result;
    }

    @Override
    public List<Transaction> getTransactions(long accountId) {
        Account account = this.accountEntityFacade.findByAccountId(accountId);
        return this.transactionEntityFacade.find(account);
    }






    private boolean validatePersonKey(long personKey) {
        String result = this.httpHelper.get("http://localhost:8060/person/find", "key", Long.toString(personKey));
        System.out.println(result);
        return !result.equals("null");
    }
    private boolean validateBankName(String bankName) {
        String result = this.httpHelper.get("http://localhost:8070/bank/find", "name", bankName);
        System.out.println(result);
        return !result.equals("null");

    }
    private boolean validateType(String type) {
        return type.equals("CHECK") || type.equals("SAVINGS");
    }

    private long getBankKey(String bankName) {
        String result = this.httpHelper.get("http://localhost:8070/bank/find", "name", bankName);
        if (!result.equals("null")) {
            JsonObject bank = new Gson().fromJson(result, JsonObject.class);
            return bank.get("id").getAsLong();
        }
        return -1;
    }
}
/*
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

    @Override
    public long create(String name) {
      return personEntityFacade.create(name);
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
}
*/
