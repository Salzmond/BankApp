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
//
//        //Говорим сервису возвращать список объектов Client при вызове метода getAll
//        Mockito.when(clientService.getAll()).thenReturn(List.of(client));
//        //Конвертируем клиента в dto
//        Mockito.when(converter.toDto(client)).thenReturn(new ClientDto(client.getId(), client.getFirstName(),
//                client.getLastName(), null));
//        //Вызываем метод контроллера по адресу /clients
//        mockMvc.perform(MockMvcRequestBuilders.get("/clients").contentType(MediaType.APPLICATION_JSON))
//                //Выводим запросы и ответы в консоль
//                .andDo(MockMvcResultHandlers.print())
//                //Ожидаем что такой метод вернет код ответа 200
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                //через метод content() можем получить тело ответа в json
//                //конвертируем список ожидаемый список в json строку и сравним с тем что
//                //отдано в теле ответа
//                //Можно сравнивать по поиску значений отдельных полей в теле ответа
//                //будет показано в тесте создания клиента
//                .andExpect(MockMvcResultMatchers.content().json(asJsonString(List.of(new ClientDto(
//                        client.getId(), client.getFirstName(), client.getLastName(), null)))));
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