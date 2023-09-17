package org.example.service;

import org.example.entity.Account;
import org.example.entity.Transaction;

import java.util.List;

public interface AccountService {

    List<Account> getAll();

    Account getByIban(String iban);

    List<Account> searchByClient(String firstName, String lastName);

    Account create(Account account);

    List<Transaction> transactionHistory(String iban);

    String retrievingAccountBalance(String iban);

    void deleteAccountByIban(String iban);
}