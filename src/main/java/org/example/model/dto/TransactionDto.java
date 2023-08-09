package org.example.model.dto;

import org.example.entity.Account;
import org.example.model.enums.TransactionType;

import java.sql.Timestamp;
import java.util.Date;

public class TransactionDto {

    private long id;
    private Account debitAccount;
    private Account creditAccount;
    private TransactionType transactionType;
    private double amount;
    private String description;
    private Timestamp createdAt = new Timestamp(new Date().getTime());

    public TransactionDto() {
        //
    }

    public TransactionDto(long id, Account debitAccount, Account creditAccount,
                          TransactionType transactionType, double amount, String description,
                          Timestamp createdAt) {
        this.id = id;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account creditAccount) {
        this.creditAccount = creditAccount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", debitAccount=" + debitAccount +
                ", creditAccount=" + creditAccount +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}