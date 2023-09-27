package org.example.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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


    @SecurityRequirement(name = "basicauth")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public List<ClientDto> getAll() {
        return clientService.getAll().stream()
                .map(client -> converter.toDto(client)).collect(Collectors.toList());
    }

    @SecurityRequirement(name = "basicauth")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('MANAGER', 'USER')")
    public ClientDto create(@RequestBody ClientCreateDto client) {
        return converter.toDto(clientService.create(converterCreate.toEntity(client)));
    }

    @SecurityRequirement(name = "basicauth")
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('MANAGER', 'USER')")
    public ClientDto update(@RequestBody ClientUpdateDto client) {
        return converter.toDto(clientService.update(converterUpdate.toEntity(client)));
    }

    @SecurityRequirement(name = "basicauth")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ClientDto getById(@PathVariable("id") long id) {
        return converter.toDto(clientService.getById(id));
    }

    @SecurityRequirement(name = "basicauth")
    @GetMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('USER')")
    public ClientDto getCurrent() {
        return converter.toDto(clientService.getCurrent());
    }

    @SecurityRequirement(name = "basicauth")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('MANAGER')")
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