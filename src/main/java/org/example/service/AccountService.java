package org.example.service;

import org.example.entity.Account;
import org.example.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    List<Account> getAll();

    Account getByIban(String iban);

    List<Account> search(String firstName, String lastName);

    Account create(Account account);

    List<Transaction> transactionHistory(String iban);

    void retrievingAccountBalance(String iban);

    void deleteAccountById(String iban);
}