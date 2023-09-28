package org.example.service;

import org.example.configuration.TestSecurityConfig;
import org.example.entity.Account;
import org.example.exception.AccountExistsException;
import org.example.model.dto.AccountBalanceInfoDto;
import org.example.model.enums.CurrencyCode;
import org.example.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = TestSecurityConfig.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository repository;

    @Mock
    private ClientServiceImpl clientService;

    @Mock
    private GenerateIban generateIban;

    @InjectMocks
    private AccountServiceImpl accountService;

    private List<Account> accounts;

    @BeforeEach
    void init() {
        accounts = Arrays.asList(
                new Account("DE1234567890", "Test account", BigDecimal.valueOf(1000), CurrencyCode.CHF),
                new Account("DE987654321", "Test account", BigDecimal.valueOf(1000), CurrencyCode.CHF));
    }

    @Test
    void getAll() {
        Mockito.when(repository.findAll()).thenReturn(accounts);
        assertEquals(2, accountService.getAll().size());
    }

    @Test
    void getByIbanWhenAccountExists() {
        Mockito.when(repository.findById(accounts.get(0).getIban())).thenReturn(Optional.ofNullable(accounts.get(0)));
        assertEquals("DE1234567890", accountService.getByIban(accounts.get(0).getIban()).getIban());
    }

    @Test
    void getByIbanWhenAccountNotExists() {
        Mockito.when(repository.findById("DE9876543210")).thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.getByIban("DE9876543210"));
    }

    @Test
    void createAccountWhenAccountExists() {
        Mockito.when(repository.findById(accounts.get(0).getIban())).thenReturn(Optional.ofNullable(accounts.get(0)));
        Account account = new Account("DE1234567890", "Test account", BigDecimal.valueOf(1000), CurrencyCode.CHF);
        Assertions.assertThrows(AccountExistsException.class, () -> accountService.create(account));
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
        Mockito.when(repository.findById(accounts.get(0).getIban())).thenReturn(Optional.ofNullable(accounts.get(0)));
        AccountBalanceInfoDto accountBalanceInfoDto = accountService.retrievingAccountBalance(accounts.get(0).getIban());
        assertEquals(1000, accountBalanceInfoDto.getAmount());
        assertEquals(CurrencyCode.CHF, accountBalanceInfoDto.getCurrency());
    }

    @Test
    void deleteAccountByIbanWhenIbanNotExists() {
        Mockito.when(repository.findById("DE1111111")).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.deleteAccountByIban("DE1111111"));
    }
}