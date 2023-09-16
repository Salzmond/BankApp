package org.example.service;

import org.example.entity.Transaction;
import org.example.model.enums.CurrencyCode;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface TransactionService {

    Transaction getById(long id);

    List<Transaction> search(double amount);

    Transaction transferMoneyBetweenAccounts(String ibanFrom, String ibanTo, double amount, String description);

    Double getCurrencyRate(String from, String to);
}