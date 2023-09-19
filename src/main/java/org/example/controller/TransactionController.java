package org.example.controller;

import org.example.model.dto.TransactionCreateDto;
import org.example.model.dto.TransactionDto;
import org.example.service.TransactionService;
import org.example.service.dtoconverter.TransactionDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDto> searchByAmount(@PathVariable("amount") double amount) {
        return transactionService.search(amount)
                .stream().map(transaction -> converter.toDto(transaction)).collect(Collectors.toList());
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto transferMoney(@RequestBody TransactionCreateDto transactionCreate) {
        return converter.toDto(transactionService.transferMoneyBetweenAccounts(transactionCreate.getIbanFrom(),
                transactionCreate.getIbanTo(), transactionCreate.getAmount(), transactionCreate.getDescription()));
    }
}