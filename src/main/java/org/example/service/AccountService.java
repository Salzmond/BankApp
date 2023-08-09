package org.example.service;

import org.example.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAll();

    Account getById(long id);

    List<Account> search(String name, int status);

    Account create(Account account);

    void deleteAccountById(long id);
}