package org.example.model.dto;

import org.example.model.enums.TransactionType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class TransactionDto {

    private long id;
    private AccountDto debitAccount;
    private AccountDto creditAccount;
    private TransactionType transactionType;
    private BigDecimal amount;
    private String description;
    private Timestamp createdAt;

    public TransactionDto() {
        //
    }

    public TransactionDto(long id, AccountDto debitAccount, AccountDto creditAccount,
                          TransactionType transactionType, BigDecimal amount, String description) {
        this.id = id;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
        this.createdAt = new Timestamp(new Date().getTime());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AccountDto getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(AccountDto debitAccount) {
        this.debitAccount = debitAccount;
    }

    public AccountDto getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(AccountDto creditAccount) {
        this.creditAccount = creditAccount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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