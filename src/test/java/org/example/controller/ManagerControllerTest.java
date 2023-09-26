package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Manager;
import org.example.model.dto.ManagerDto;
import org.example.service.ManagerService;
import org.example.service.dtoconverter.ManagerDtoConverter;
import org.hamcrest.core.Is;
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

@WebMvcTest(ManagerController.class)
class ManagerControllerTest {

    @MockBean
    private ManagerService managerService;

    @MockBean
    private ManagerDtoConverter converter;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        Manager manager = new Manager(1L, "Peter", "Pen", 50);
        Mockito.when(managerService.getAll()).thenReturn(List.of(manager));
        ManagerDto managerDto = new ManagerDto(manager.getId(),
                manager.getFirstName(), manager.getLastName(), manager.getStatus());
        Mockito.when(converter.toDto(manager)).thenReturn(managerDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/managers").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(List.of(managerDto))));
    }

    @Test
    void create() {
    }

    @Test
    void getById() {
    }

    @Test
    void deleteById() {
    }

   
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
