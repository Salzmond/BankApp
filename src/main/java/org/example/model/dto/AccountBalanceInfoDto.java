package org.example.model.dto;

import org.example.model.enums.CurrencyCode;

public class AccountBalanceInfoDto {

    private Double amount;

    private CurrencyCode currency;

    public AccountBalanceInfoDto(Double amount, CurrencyCode currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public CurrencyCode getCurrency() {
        return currency;
    }
}
