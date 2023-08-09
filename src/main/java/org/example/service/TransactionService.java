package org.example.service;

import org.example.entity.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionService {

    List<Transaction> getAll();

    Transaction getById(long id);

    List<Transaction> search(double amount, String createdAt);

    Transaction create(Transaction Transaction);
}