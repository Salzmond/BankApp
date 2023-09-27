package org.example.controller;

import org.example.entity.Account;
import org.example.entity.Client;
import org.example.service.AccountService;
import org.example.service.dtoconverter.AccountDtoConverter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @MockBean
    private AccountDtoConverter dtoConverter;

    @MockBean
    private AccountService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() {
        Account account = new Account();
        Client client = new Client();
        client.setId(1);

    }

    @Test
    void create() {
    }

    @Test
    void getByIban() {
    }

    @Test
    void deleteByIban() {
    }
}
