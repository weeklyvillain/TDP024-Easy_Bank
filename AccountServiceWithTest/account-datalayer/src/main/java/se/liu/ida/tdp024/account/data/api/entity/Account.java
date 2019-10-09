package se.liu.ida.tdp024.account.data.api.entity;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public interface Account extends Serializable {
    public long getId();
    public long getPersonKey();
    public long getBankKey();
    public String getType();
    public int getHoldings();

    public void setHoldings(int newHoldings);
    public void setPersonKey(long personKey);
    public void setType(String type);
    public void setBankKey(long bankKey);
}

/*
public interface Person extends Serializable {

    public long getId();

    public String getName();

    public void setName(String name);
}
*/
