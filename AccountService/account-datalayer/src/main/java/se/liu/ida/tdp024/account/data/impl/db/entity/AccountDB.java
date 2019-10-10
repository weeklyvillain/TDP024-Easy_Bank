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

/*
    public AccountDB(Account that) {
        this.id = that.getId();
        this.personKey = that.getPersonKey();
        this.accountType = that.getType();
        this.bankKey = that.getBankKey();
        this.holdins = that.getHoldings();
    }
    */

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
