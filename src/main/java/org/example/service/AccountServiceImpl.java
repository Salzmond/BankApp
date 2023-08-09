package org.example.service;

import org.example.entity.Account;
import org.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Account getById(long id) {
        return accountRepository.getReferenceById(id);
    }

    @Override
    public List<Account> search(String name, int status) {
        return accountRepository.search(name, status);
    }

    @Override
    public Account create(Account account) {
        Account accountEntity;
        accountEntity = getById(account.getId());
        if (accountEntity == null) {
            accountEntity = accountRepository.save(account);
        }
        return accountEntity;
    }

    @Override
    public void deleteAccountById(long id) {
        accountRepository.deleteById(id); //?
    }
}