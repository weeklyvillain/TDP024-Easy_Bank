package se.liu.ida.tdp024.bank.data.api.entity;

import java.io.Serializable;

public interface Bank extends Serializable {

    public long getId();

    public String getName();

    public void setName(String name);
}
