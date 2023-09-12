package org.example.model.dto;

import org.example.entity.Manager;
import org.example.model.enums.CurrencyCode;
import org.example.model.enums.ProductStatus;

import java.sql.Timestamp;
import java.util.Date;

public class ProductDto {

    private long id;
    private ManagerDto manager;
    private String name;
    private ProductStatus status;
    private CurrencyCode currencyCode;
    private double interestRate;
    private int productLimit;
    private Timestamp createdAt = new Timestamp(new Date().getTime());
    private Timestamp updatedAt = new Timestamp(new Date().getTime());

    public ProductDto() {
        //
    }

    public ProductDto(long id, ManagerDto manager, String name, ProductStatus status,
                      CurrencyCode currencyCode, double interestRate, int productLimit,
                      Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.manager = manager;
        this.name = name;
        this.status = status;
        this.currencyCode = currencyCode;
        this.interestRate = interestRate;
        this.productLimit = productLimit;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ProductDto(ManagerDto manager, String name, ProductStatus status, CurrencyCode currencyCode,
                      double interestRate, int productLimit, Timestamp createdAt, Timestamp updatedAt) {
        this.manager = manager;
        this.name = name;
        this.status = status;
        this.currencyCode = currencyCode;
        this.interestRate = interestRate;
        this.productLimit = productLimit;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getProductLimit() {
        return productLimit;
    }

    public void setProductLimit(int productLimit) {
        this.productLimit = productLimit;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", manager=" + manager +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", currencyCode=" + currencyCode +
                ", interestRate=" + interestRate +
                ", limit=" + productLimit +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}