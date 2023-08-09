package org.example.model.dto;

import org.example.entity.Account;
import org.example.entity.Product;

import java.sql.Timestamp;
import java.util.Date;

public class AgreementDto {

    private long id;
    private Account account;
    private Product product;
    private double interestRate;
    private int status;
    private double sum;
    private Timestamp createdAt = new Timestamp(new Date().getTime());
    private Timestamp updatedAt = new Timestamp(new Date().getTime());

    public AgreementDto(long id, Account account, Product product, double interestRate,
                        int status, double sum, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.account = account;
        this.product = product;
        this.interestRate = interestRate;
        this.status = status;
        this.sum = sum;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Agreement{" +
                "id=" + id +
                ", account=" + account +
                ", product=" + product +
                ", interestRate=" + interestRate +
                ", status=" + status +
                ", sum=" + sum +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}