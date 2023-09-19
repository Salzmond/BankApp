package org.example.service;

import org.example.entity.Account;
import org.example.model.dto.AccountBalanceInfoDto;
import org.example.model.enums.CurrencyCode;
import org.example.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void getAll() {
        Account accountOne = new Account("DE1234567890", "Test account", BigDecimal.valueOf(1000), CurrencyCode.CHF);
        Account accountTwo = new Account("DE987654321", "Test account", BigDecimal.valueOf(1000), CurrencyCode.CHF);
        Mockito.when(accountService.getAll()).thenReturn(List.of(accountOne, accountTwo));
        Assertions.assertEquals(2, accountService.getAll().size());
    }

    @Test
    void getByIbanWhenAccountExists() {
        Account account = new Account("DE1234567890", "Test account", BigDecimal.valueOf(1000), CurrencyCode.CHF);
        Mockito.when(repository.findById(account.getIban())).thenReturn(Optional.of(account));
        Assertions.assertEquals("DE1234567890", accountService.getByIban(account.getIban()).getIban());
    }

    @Test
    void getByIbanWhenAccountNotExists() {
        Mockito.when(repository.findById("DE9876543210")).thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.getByIban("DE9876543210"));
    }

    @Test
    void createAccountWhenAccountExists() {
        Account account = new Account("DE555555555", "Test account", BigDecimal.valueOf(1000), CurrencyCode.CHF);
        Mockito.when(repository.findById(account.getIban())).thenReturn(Optional.of(account));
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.create(account));
    }

    @Test
    void createAccountWhenAccountNotExists() {
        Mockito.when(repository.findById("DE555555555")).thenReturn(null);
//        Account account = new Account("DE555555555", "Test account", BigDecimal.valueOf(1000), CurrencyCode.CHF);
//        Mockito.when(repository.save(account)).thenReturn(account);
//        Assertions.assertEquals("DE555555555", accountService.create(account).getIban());
    }

    @Test
    void transactionHistory() {
    }

    @Test
    void retrievingAccountBalance() {
        Account account = new Account("DE1234567890", "Test account", BigDecimal.valueOf(1000), CurrencyCode.CHF);
        Mockito.when(repository.findById(account.getIban())).thenReturn(Optional.of(account));
        AccountBalanceInfoDto accountBalanceInfoDto = accountService.retrievingAccountBalance(account.getIban());
        Assertions.assertEquals(1000, accountBalanceInfoDto.getAmount());
        Assertions.assertEquals(CurrencyCode.CHF, accountBalanceInfoDto.getCurrency());
    }

    @Test
    void deleteAccountByIbanWhenIbanNotExists() {
        Mockito.when(repository.findById("DE1111111")).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.deleteAccountByIban("DE1111111"));
    }
}