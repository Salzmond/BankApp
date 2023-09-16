package org.example.controller;

import org.example.entity.Transaction;
import org.example.model.dto.TransactionCreateDto;
import org.example.model.dto.TransactionDto;
import org.example.service.TransactionService;
import org.example.service.dtoconverter.TransactionDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionDtoConverter converter;

    //https://api.exchangerate-api.com/v4/latest/usd
    //http://localhost:8080/transactions/convert/usd
    @GetMapping("/currency_rate/{from}/{to}")
    public Double getCurrencyRate(@PathVariable("from") String from,
                                  @PathVariable("to") String to) {
        return transactionService.getCurrencyRate(from, to);
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransactionDto transferMoney(@RequestBody TransactionCreateDto transactionCreate) {
        return converter.toDto(transactionService.transferMoneyBetweenAccounts(transactionCreate.getIbanFrom(),
                transactionCreate.getIbanTo(),transactionCreate.getAmount(), transactionCreate.getDescription()));
    }


}
