package se.liu.ida.tdp024.account.data.api.entity;

import java.io.Serializable;

public interface Account extends Serializable {
    public long getId();
    public String getPersonKey();
    public String getType();
    public String getBankKey();
    public long getHoldings();
    public void setHoldings(long newHoldings);
}

/*
public interface Person extends Serializable {

    public long getId();

    public String getName();

    public void setName(String name);
}
*/
