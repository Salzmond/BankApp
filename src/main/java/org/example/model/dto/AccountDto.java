package org.example.model.dto;

import org.example.entity.Client;
import org.example.model.enums.AccountStatus;
import org.example.model.enums.AccountType;
import org.example.model.enums.CurrencyCode;

import java.sql.Timestamp;
import java.util.Date;

public class AccountDto {
    private long id;
    private Client client;
    private String name;
    private AccountType type;
    private AccountStatus status;
    private double balance;
    private CurrencyCode currencyCode;
    private Timestamp createdAt = new Timestamp(new Date().getTime());
    private Timestamp updatedAt = new Timestamp(new Date().getTime());

    public AccountDto(long id, Client client, String name, AccountType type,
                      AccountStatus status, double balance,
                      CurrencyCode currencyCode, Timestamp createdAt,
                      Timestamp updatedAt) {
        this.id = id;
        this.client = client;
        this.name = name;
        this.type = type;
        this.status = status;
        this.balance = balance;
        this.currencyCode = currencyCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", client=" + client +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", balance=" + balance +
                ", currencyCode=" + currencyCode +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}