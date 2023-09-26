package org.example.controller;

import org.example.model.dto.ClientCreateDto;
import org.example.model.dto.ClientDto;
import org.example.model.dto.ClientUpdateDto;
import org.example.service.ClientService;
import org.example.service.dtoconverter.ClientCreateDtoConverter;
import org.example.service.dtoconverter.ClientDtoConverter;
import org.example.service.dtoconverter.ClientUpdateDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientDtoConverter converter;

    @Autowired
    private ClientUpdateDtoConverter converterUpdate;

    @Autowired
    private ClientCreateDtoConverter converterCreate;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public List<ClientDto> getAll() {
        return clientService.getAll().stream()
                .map(client -> converter.toDto(client)).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto create(@RequestBody ClientCreateDto client) {
        return converter.toDto(clientService.create(converterCreate.toEntity(client)));
    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto update(@PathVariable("id") long id, @RequestBody ClientUpdateDto client) {
        return converter.toDto(clientService.update(id, converterUpdate.toEntity(client)));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ClientDto getById(@PathVariable("id") long id) {
        return converter.toDto(clientService.getById(id));
    }

    @GetMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto getCurrent() {
        return converter.toDto(clientService.getCurrent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") long id) {
        clientService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> exceptionHandler(ConstraintViolationException ex) {
        Map<String, String> map = new HashMap<>();
        ex.getConstraintViolations().forEach(error ->
                map.put(error.getPropertyPath().toString(), error.getMessage()));
        return map;
    }
}