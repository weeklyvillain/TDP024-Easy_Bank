package se.liu.ida.tdp024.account.data.impl.db.entity;

import javax.persistence.Entity;
import javax.persistence.Column;
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
import se.liu.ida.tdp024.account.data.impl.db.entity.AccountDB;

@Entity
public class TransactionDB implements Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int amount;
    private String type;
    private String created;
    private String status;

    @ManyToOne
    private AccountDB account;

    @PrePersist
    protected void onCreate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        this.created = dateFormat.format(new Date());
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void setAccount(Account account) {
        this.account = (AccountDB) account;
    }
}
