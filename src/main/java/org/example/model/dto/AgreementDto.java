package org.example.model.dto;

import org.example.entity.Account;
import org.example.entity.Product;

import java.sql.Timestamp;
import java.util.Date;

public class AgreementDto {

    private long id;
    private AccountDto account;
    private ProductDto product;
    private double interestRate;
    private int status;
    private double sum;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public AgreementDto(AccountDto account, ProductDto product, double interestRate,
                        int status, double sum) {
        this.account = account;
        this.product = product;
        this.interestRate = interestRate;
        this.status = status;
        this.sum = sum;
        this.createdAt = new Timestamp(new Date().getTime());
        this.updatedAt = new Timestamp(new Date().getTime());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
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