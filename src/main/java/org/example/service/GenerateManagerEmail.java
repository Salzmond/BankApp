package org.example.service;

import org.springframework.stereotype.Service;

@Service
public class GenerateManagerEmail {

    private final String EMAIL = "@mybank.com";

    public String generate(String firstName, String lastName) {
        String email = firstName.toLowerCase().concat(".").concat(lastName.toLowerCase())
                .concat(EMAIL);
        return email;
    }
}