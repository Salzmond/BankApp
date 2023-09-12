package org.example.repository;

import org.example.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT trans FROM Transaction trans WHERE trans.amount between :amountLow AND :amountHigh")
    List<Transaction> search(@Param("amountLow") double amountLow, @Param("amountHigh") double amountHigh);
}