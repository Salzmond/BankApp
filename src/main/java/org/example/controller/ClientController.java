package org.example.controller;

import org.example.model.dto.ClientDto;
import org.example.service.ClientService;
import org.example.service.dtoconverter.ClientDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientDtoConverter converter;

    @GetMapping
    List<ClientDto> getAll() {
        return clientService.getAll().stream()
                .map(client -> converter.toDto(client)).collect(Collectors.toList());
    }

    @PostMapping
    public ClientDto create(@RequestBody ClientDto clientDto) {
        return converter.toDto(clientService.create(converter.toEntity(clientDto)));
    }

    @GetMapping("/{id}")
    public ClientDto getById(@PathVariable("id") long id) {
        return converter.toDto(clientService.getById(id));
    }
}