package org.example.service;

import org.example.entity.Account;
import org.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public List<Account> search(String firstName, String lastName) {
        List<Account> accounts = accountRepository.search(firstName, lastName);
        if(accounts.isEmpty()) {
            throw new IllegalArgumentException("No account found");
        }
        return accounts;
    }

    @Override
    public Account create(Account account) {
        Account accountEntity = getByIban(account.getIban());
        if (accountEntity != null) {
            throw new IllegalStateException("This account already exists in system");
        }
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccountById(String iban) {
        accountRepository.delete(getByIban(iban));
    }
}