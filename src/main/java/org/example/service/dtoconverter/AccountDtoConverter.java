package org.example.service.dtoconverter;

import org.example.entity.Account;
import org.example.model.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDtoConverter implements Converter<AccountDto, Account> {

    @Autowired
    private ClientDtoConverter clientDtoConverter;

    public AccountDto toDto(Account account) {
        if (account == null) {
            return null;
        }
        return new AccountDto(account.getIban(), clientDtoConverter.toDto(account.getClient()), account.getName(),
                account.getType(), account.getStatus(), account.getBalance(), account.getCurrencyCode());
    }

    public Account toEntity(AccountDto accountDto) {
        return new Account(accountDto.getIban(), accountDto.getClient() == null ?
                clientDtoConverter.toEntity(accountDto.getClient()) : null,
                accountDto.getName(),
                accountDto.getType(), accountDto.getStatus(), accountDto.getBalance(), accountDto.getCurrencyCode());
    }
}