package org.example.service.dtoconverter;

import org.example.entity.Transaction;
import org.example.model.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionDtoConverter implements Converter<TransactionDto, Transaction> {

    @Autowired
    private AccountDtoConverter accountDtoConverter;

    public TransactionDto toDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        return new TransactionDto(transaction.getId(), accountDtoConverter.toDto(transaction.getDebitAccount()),
                accountDtoConverter.toDto(transaction.getCreditAccount()),
                transaction.getTransactionType(),
                transaction.getAmount(), transaction.getDescription());
    }

    public Transaction toEntity(TransactionDto transactionDto) {
        return new Transaction(transactionDto.getId(),
                transactionDto.getCreditAccount() == null ?
                        accountDtoConverter.toEntity(transactionDto.getCreditAccount()) : null,
                transactionDto.getDebitAccount() == null ?
                        accountDtoConverter.toEntity(transactionDto.getDebitAccount()) : null,
                transactionDto.getTransactionType(),
                transactionDto.getAmount(), transactionDto.getDescription());
    }
}