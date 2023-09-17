package org.example.service;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getByIban(String iban) {
        return accountRepository.findById(iban)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Account with IBAN %s not found", iban)));
    }

    @Override
    public List<Account> searchByClient(String firstName, String lastName) {
        List<Account> accounts = accountRepository.search(firstName, lastName);
        if (accounts.isEmpty()) {
            throw new IllegalArgumentException("No account found");
        }
        return accounts;
    }

    @Override
    public Account create(Account account) {
        Account accountEntity = getByIban(account.getIban());
        if (accountEntity != null) {
            throw new IllegalArgumentException("This account already exists in system");
        }
        return accountRepository.save(account);
    }

    @Override
    public List<Transaction> transactionHistory(String iban) {
        Account account = getByIban(iban);
        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(account.getDebitTransactions());
        transactions.addAll(account.getCreditTransactions());
        Collections.sort(transactions, Comparator.comparing(Transaction::getCreatedAt));
        return transactions;
    }

    @Override
    public String retrievingAccountBalance(String iban) {
        Account account = getByIban(iban);
        String balance = account.getBalance() + " " + account.getCurrencyCode();
        return balance;
    }

    @Override
    public void deleteAccountByIban(String iban) {
        accountRepository.delete(getByIban(iban));
    }
}