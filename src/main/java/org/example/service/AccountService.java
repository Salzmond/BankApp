package org.example.service;

import org.example.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    List<Account> getAll();

    Account getByIban(String iban);

    List<Account> search(String firstName, String lastName);

    Account create(Account account);

    void deleteAccountById(String iban);
}