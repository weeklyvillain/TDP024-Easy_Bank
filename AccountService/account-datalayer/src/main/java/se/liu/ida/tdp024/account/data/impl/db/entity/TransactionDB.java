package se.liu.ida.tdp024.account.data.impl.db.entity;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import se.liu.ida.tdp024.account.data.api.entity.Transaction;
import se.liu.ida.tdp024.account.data.api.entity.Account;

@Entity
public class TransactionDB implements Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int amount;
    private String type;
    private String created;
    private String status;
    private Account account;

    @PrePersist
    protected void onCreate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        this.created = dateFormat.format(new Date());
    }

    @Override
    public long getId() {
        return this.id;
    }
    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }
    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String getType() {
        return this.type;
    }
    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDate() {
        return this.created;
    }

    @Override
    public String getStatus() {
        return this.status;
    }
    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public Account getAccount() {
        return this.account;
    }
    @Override
    public void setAccount(Account account) {
        this.account = account;
    }
}
