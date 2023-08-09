package org.example.service.dtoconverter;

import org.example.entity.Transaction;
import org.example.model.dto.TransactionDto;
import org.springframework.stereotype.Service;

@Service
public class TransactionDtoConverter {

    public TransactionDto toDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        return new TransactionDto(transaction.getId(), transaction.getDebitAccount(),
                transaction.getCreditAccount(), transaction.getTransactionType(),
                transaction.getAmount(), transaction.getDescription(), transaction.getCreatedAt());
    }

    public Transaction toEntity(TransactionDto transactionDto) {
        return new Transaction(transactionDto.getId(), transactionDto.getDebitAccount(),
                transactionDto.getCreditAccount(), transactionDto.getTransactionType(),
                transactionDto.getAmount(), transactionDto.getDescription(), transactionDto.getCreatedAt());
    }
}