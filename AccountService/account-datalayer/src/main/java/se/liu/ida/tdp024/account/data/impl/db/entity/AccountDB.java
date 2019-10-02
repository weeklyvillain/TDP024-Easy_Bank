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
    private String personKey;
    private String accountType;
    private String bankKey;
    private long holdings;


    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public String getPersonKey() {
        return this.personKey;
    }

    @Override
    public String getType() {
        return this.accountType;
    }

    @Override
    public String getBankKey() {
        return this.bankKey;
    }

    @Override
    public long getHoldings() {
        return this.holdings;
    }

    @Override
    public void setHoldings(long newHoldings) {
        this.holdings = newHoldings;
    }
}
/*
@Entity
public class PersonDB implements Person {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String name;

  // --- Getters and Setters --- //
  @Override
  public long getId() {
      return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }
}
*/
