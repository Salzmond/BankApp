package org.example.repository;

import org.example.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT trans FROM Transaction trans WHERE trans.amount = :amount AND trans.createdAt = :createdAt")
    List<Transaction> search(@Param("amount") double amount, @Param("createdAt") String createdAt);
}