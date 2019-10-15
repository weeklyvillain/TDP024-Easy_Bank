package se.liu.ida.tdp024.account.data.impl.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import se.liu.ida.tdp024.account.data.api.entity.Account;

@Entity
public class AccountDB implements Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long personKey;
    private String accountType;
    private long bankKey;
    private int holdings;


    @Override
    public void setPersonKey(long personKey) {
        this.personKey = personKey;
    }

    @Override
    public void setType(String type) {
        this.accountType = type;
    }

    @Override
    public void setBankKey(long bankKey) {
        this.bankKey = bankKey;
    }

    @Override
    public int getHoldings() {
        return this.holdings;
    }
    @Override
    public void setHoldings(int newHoldings) {
        this.holdings = newHoldings;
    }
}
