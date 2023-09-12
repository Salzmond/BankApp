package org.example.service;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.exception.AccountNotActiveException;
import org.example.exception.UnsupportedTransactionException;
import org.example.model.enums.AccountStatus;
import org.example.model.enums.TransactionType;
import org.example.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final static double RANGE = 50;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public Transaction getById(long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Transaction with id %d not found", id)));
    }
    @Override
    public List<Transaction> search(double amount) {
        List<Transaction> transactions = transactionRepository.search((amount - RANGE), (amount + RANGE));
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException("No transaction found");
        }
        return transactions;
    }

    @Override
    public Transaction putMoneyIntoTheAccountViaATM(String iban, int amount) {
        Account account = accountService.getByIban(iban);
        if (!account.getStatus().equals(AccountStatus.ACTIVE)) {
            throw new AccountNotActiveException("You account not active");
        }
        account.setBalance(account.getBalance().add(BigDecimal.valueOf(amount)));
        return transactionRepository.save(new Transaction(account, TransactionType.REPLENISHMENT, BigDecimal.valueOf(amount)));
    }

    @Override
    public Transaction withdrawMoneyFromTheAccountViaATM(String iban, int amount) {
        Account account = accountService.getByIban(iban);
        if (account.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0) {
            throw new UnsupportedTransactionException("You have not enough money on your account");
        }
        account.setBalance(account.getBalance().subtract(BigDecimal.valueOf(amount)));
        return transactionRepository.save(new Transaction(account, TransactionType.WITHDRAWAL, BigDecimal.valueOf(amount)));
    }

    @Override
    public Transaction transferMoneyBetweenAccounts(String ibanFrom, String ibanTo, double amount, String description) {
        Account accountFrom = accountService.getByIban(ibanFrom);
        Account accountTo = accountService.getByIban(ibanTo);
        if (accountFrom.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0) {
            throw new UnsupportedTransactionException("You have not enough money on your account");
        }
        accountFrom.setBalance(accountFrom.getBalance().subtract(BigDecimal.valueOf(amount)));
        accountTo.setBalance(accountTo.getBalance().add(BigDecimal.valueOf(amount)));
        return transactionRepository.save(new Transaction(accountFrom, accountTo, TransactionType.WITHDRAWAL, BigDecimal.valueOf(amount), description));
    }

    @Override
    public Transaction cancelTransaction(long id) {
        return null;
    }

    @Override
    public void deleteTransaction(long id) {
        transactionRepository.delete(getById(id));
    }
}