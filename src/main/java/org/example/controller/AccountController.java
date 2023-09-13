package org.example.controller;

import org.example.entity.Account;
import org.example.model.dto.AccountDto;
import org.example.service.AccountService;
import org.example.service.dtoconverter.AccountDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> getAll() {
        return accountService.getAll().stream()
                .map(account -> converter.toDto(account)).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto create(@RequestBody AccountDto accountDto) {
        return converter.toDto(accountService.create(converter.toEntity(accountDto)));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public AccountDto getById(@PathVariable("id") String iban) {
        return converter.toDto(accountService.getByIban(iban));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") String iban) {
        accountService.deleteAccountById(iban);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) //for Validation
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> exceptionHandler(ConstraintViolationException ex) {
        Map<String, String> map = new HashMap<>();
        ex.getConstraintViolations().forEach(error ->
                map.put(error.getPropertyPath().toString(), error.getMessage()));
        return map;
    }
}