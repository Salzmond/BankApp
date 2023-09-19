package org.example.service;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.exception.UnsupportedTransactionException;
import org.example.model.enums.TransactionType;
import org.example.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

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
                .orElseThrow(() -> new EntityNotFoundException(String.format("Transaction with id %d not found", id)));
    }

    @Override
    public List<Transaction> search(double amount) {
        List<Transaction> transactions = transactionRepository.search((amount - RANGE), (amount + RANGE));
        if (transactions.isEmpty()) {
            throw new EntityNotFoundException("No transaction found");
        }
        return transactions;
    }

    @Transactional
    @Override
    public Transaction transferMoneyBetweenAccounts(String ibanFrom, String ibanTo, double amount, String description) {
        Account accountFrom = accountService.getByIban(ibanFrom);
        if (accountFrom.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0) {
            throw new UnsupportedTransactionException("You have not enough money on your account");
        }
        Account accountTo = accountService.getByIban(ibanTo);

        accountFrom.setBalance(accountFrom.getBalance().subtract(BigDecimal.valueOf(amount)));
        accountFrom.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        Double currencyRate = getCurrencyRate(accountFrom.getCurrencyCode().name(), accountTo.getCurrencyCode().name());

        accountTo.setBalance(accountTo.getBalance().add(BigDecimal.valueOf(amount * currencyRate)));
        accountTo.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        Transaction transaction = new Transaction(accountFrom, accountTo, TransactionType.SUCCESS, BigDecimal.valueOf(amount), description);
        Transaction transactionEntity = transactionRepository.save(transaction);
        log.debug("Transaction {}", transactionEntity);
        return transactionEntity;
    }

    private Double getCurrencyRate(String from, String to) {
        Map<String, Double> currencyMap = currencyApiService.getCurrencyMap(from);
        return currencyMap.get(to.toUpperCase());
    }
}