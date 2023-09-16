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

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final static double RANGE = 50;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CurrencyApiService currencyApiService;

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
    @Transactional
    @Override
    public Transaction transferMoneyBetweenAccounts(String ibanFrom, String ibanTo, double amount, String description) {
        Account accountFrom = accountService.getByIban(ibanFrom);
        Account accountTo = accountService.getByIban(ibanTo);
        if (accountFrom.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0) {
            throw new UnsupportedTransactionException("You have not enough money on your account");
        }
        accountFrom.setBalance(accountFrom.getBalance().subtract(BigDecimal.valueOf(amount)));
        accountFrom.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        Double currencyRate = getCurrencyRate(accountFrom.getCurrencyCode().name(), accountTo.getCurrencyCode().name());
        accountTo.setBalance(accountTo.getBalance().add(BigDecimal.valueOf(amount * currencyRate)));

        return transactionRepository.save(new Transaction(accountFrom, accountTo, TransactionType.WITHDRAWAL, BigDecimal.valueOf(amount), description));
    }
    @Override
    public Double getCurrencyRate(String from, String to) {
        Map<String, Double> currencyMap = currencyApiService.getCurrencyMap(from);
        return currencyMap.get(to.toUpperCase());
    }
}