package org.example.service;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.model.dto.AccountBalanceInfoDto;

import java.util.List;

public interface AccountService {

    List<Account> getAll();

    Account getByIban(String iban);

    List<Account> searchByClient(String firstName, String lastName);

    Account create(Account account);

    List<Transaction> transactionHistory(String iban);

    AccountBalanceInfoDto retrievingAccountBalance(String iban);

    void deleteAccountByIban(String iban);
}