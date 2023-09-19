package org.example.service;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class GenerateIban {
    private final String BANK_CODE = "55555";

    private String generateAccountNumber(int start, int end) {
        String numbers = "0123456789";
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            accountNumber.append(numbers.charAt(ThreadLocalRandom.current().nextInt(start, end)));
        }
        return accountNumber.toString();
    }

    private String generateIban() {
        String iban = new Iban.Builder()
                .countryCode(CountryCode.DE)
                .bankCode(BANK_CODE)
                .accountNumber(generateAccountNumber(0,10))
                .build().toString();
        return iban;
    }
}