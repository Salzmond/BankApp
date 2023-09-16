package org.example.model.dto;

public class TransactionCreateDto {

    private String ibanFrom;
    private String ibanTo;

    private double amount;

    private String description;

    public String getIbanFrom() {
        return ibanFrom;
    }

    public String getIbanTo() {
        return ibanTo;
    }

    public double getAmount() {
        return amount;
    }


    public String getDescription() {
        return description;
    }

    public void setIbanFrom(String ibanFrom) {
        this.ibanFrom = ibanFrom;
    }

    public void setIbanTo(String ibanTo) {
        this.ibanTo = ibanTo;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}