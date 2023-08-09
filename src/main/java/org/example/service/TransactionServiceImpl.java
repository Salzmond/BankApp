package org.example.service;

import org.example.entity.Transaction;
import org.example.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getById(long id) {
        return transactionRepository.getReferenceById(id);
    }

    @Override
    public List<Transaction> search(double amount, String createdAt) {
        return transactionRepository.search(amount, createdAt);
    }

    @Override
    public Transaction create(Transaction transaction) {
        Transaction transactionEntity;
        transactionEntity = getById(transaction.getId());
        if (transactionEntity == null) {
            transactionEntity = transactionRepository.save(transaction);
        }
        return transactionEntity;
    }
}