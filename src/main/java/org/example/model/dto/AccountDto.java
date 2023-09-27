package org.example.model.dto;

import org.example.model.enums.AccountStatus;
import org.example.model.enums.AccountType;
import org.example.model.enums.CurrencyCode;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class AccountDto {

    private String iban;
    private String clientFirstName;

    private String clientLastName;
    private String name;
    private AccountType type;
    private AccountStatus status;
    private BigDecimal balance;
    private CurrencyCode currencyCode;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public AccountDto(String iban, String clientFirstName, String clientLastName, String name, AccountType type,
                      AccountStatus status, BigDecimal balance,
                      CurrencyCode currencyCode) {
        this.iban = iban;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.name = name;
        this.type = type;
        this.status = status;
        this.balance = balance;
        this.currencyCode = currencyCode;
        this.createdAt = new Timestamp(new Date().getTime());
        this.updatedAt = new Timestamp(new Date().getTime());
    }
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
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
                "iban='" + iban + '\'' +
                ", clientFirstName='" + clientFirstName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
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