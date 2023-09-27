package org.example.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.model.dto.TransactionCreateDto;
import org.example.model.dto.TransactionDto;
import org.example.service.TransactionService;
import org.example.service.dtoconverter.TransactionDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionDtoConverter converter;

    @SecurityRequirement(name = "basicauth")
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('MANAGER', 'USER')")
    public List<TransactionDto> searchByAmount(@PathVariable("amount") double amount) {
        return transactionService.search(amount)
                .stream().map(transaction -> converter.toDto(transaction)).collect(Collectors.toList());
    }

    @SecurityRequirement(name = "basicauth")
    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('USER')")
    public TransactionDto transferMoney(@RequestBody TransactionCreateDto transactionCreate) {
        return converter.toDto(transactionService.transferMoneyBetweenAccounts(transactionCreate.getIbanFrom(),
                transactionCreate.getIbanTo(), transactionCreate.getAmount(), transactionCreate.getDescription()));
    }
}