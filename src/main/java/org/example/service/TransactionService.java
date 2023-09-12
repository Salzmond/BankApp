package org.example.service;

import org.example.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    Transaction getById(long id);

    List<Transaction> search(double amount);

    Transaction putMoneyIntoTheAccountViaATM(String iban, int amount);

    Transaction withdrawMoneyFromTheAccountViaATM(String iban, int amount);

    Transaction transferMoneyBetweenAccounts(String ibanFrom, String ibanTo, double amount, String description);

    Transaction cancelTransaction(long id);

    void deleteTransaction(long id);
}