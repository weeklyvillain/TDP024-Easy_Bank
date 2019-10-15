package se.liu.ida.tdp024.account.data.api.entity;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public interface Account extends Serializable {
    public int getHoldings();

    public void setHoldings(int newHoldings);
    public void setPersonKey(long personKey);
    public void setType(String type);
    public void setBankKey(long bankKey);
}
