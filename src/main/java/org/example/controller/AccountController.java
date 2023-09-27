package org.example.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.model.dto.AccountBalanceInfoDto;
import org.example.model.dto.AccountDto;
import org.example.model.dto.TransactionDto;
import org.example.service.AccountService;
import org.example.service.dtoconverter.AccountDtoConverter;
import org.example.service.dtoconverter.TransactionDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountDtoConverter converter;

    @Autowired
    private TransactionDtoConverter transactionConverter;

    @SecurityRequirement(name = "basicauth")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public List<AccountDto> getAll() {
        return accountService.getAll().stream()
                .map(account -> converter.toDto(account)).collect(Collectors.toList());
    }

    @SecurityRequirement(name = "basicauth")
    @GetMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('USER')")
    List<AccountDto> getAllByCurrentClient() {
        return accountService.getAllByCurrentClient().stream()
                .map(account -> converter.toDto(account)).collect(Collectors.toList());
    }

    @SecurityRequirement(name = "basicauth")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('USER')")
    public AccountDto create(@RequestBody AccountDto accountDto) {
        return converter.toDto(accountService.create(converter.toEntity(accountDto)));
    }

    @SecurityRequirement(name = "basicauth")
    @GetMapping("/{iban}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public AccountDto getByIban(@PathVariable("iban") String iban) {
        return converter.toDto(accountService.getByIban(iban));
    }

    @SecurityRequirement(name = "basicauth")
    @GetMapping("/balance/{iban}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('USER')")
    public AccountBalanceInfoDto retrievingAccountBalance(@PathVariable("iban") String iban) {
        return accountService.retrievingAccountBalance(iban);
    }

    @SecurityRequirement(name = "basicauth")
    @GetMapping("/history/{iban}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('USER')")
    public List<TransactionDto> getTransactionsHistoryOfAccount(@PathVariable("iban") String iban) {
        return accountService.transactionHistory(iban).stream()
                .map(transactionConverter::toDto).collect(Collectors.toList());
    }

    @SecurityRequirement(name = "basicauth")
    @DeleteMapping("/{iban}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public void deleteByIban(@PathVariable("iban") String iban) {
        accountService.deleteAccountByIban(iban);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> exceptionHandler(ConstraintViolationException ex) {
        Map<String, String> map = new HashMap<>();
        ex.getConstraintViolations().forEach(error ->
                map.put(error.getPropertyPath().toString(), error.getMessage()));
        return map;
    }
}