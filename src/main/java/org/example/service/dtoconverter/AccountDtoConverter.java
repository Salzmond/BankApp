package org.example.service.dtoconverter;

import org.example.entity.Account;
import org.example.model.dto.AccountDto;
import org.springframework.stereotype.Service;

@Service
public class AccountDtoConverter {

    public AccountDto toDto(Account account) {
        if (account == null) {
            return null;
        }
        return new AccountDto(account.getId(), account.getClient(), account.getName(),
                account.getType(), account.getStatus(), account.getBalance(), account.getCurrencyCode(),
                account.getCreatedAt(), account.getUpdatedAt());
    }

    public Account toEntity(AccountDto accountDto) {
        return new Account(accountDto.getId(), accountDto.getClient(), accountDto.getName(),
                accountDto.getType(), accountDto.getStatus(), accountDto.getBalance(), accountDto.getCurrencyCode(),
                accountDto.getCreatedAt(), accountDto.getUpdatedAt());
    }
}