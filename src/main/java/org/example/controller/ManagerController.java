package org.example.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.model.dto.ManagerDto;
import org.example.service.ManagerService;
import org.example.service.dtoconverter.ManagerDtoConverter;
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
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ManagerDtoConverter converter;

    @SecurityRequirement(name = "basicauth")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public List<ManagerDto> getAll() {
        return managerService.getAll().stream()
                .map(manager -> converter.toDto(manager)).collect(Collectors.toList());
    }

    @SecurityRequirement(name = "basicauth")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ManagerDto create(@RequestBody ManagerDto managerDto) {
        return converter.toDto(managerService.create(converter.toEntity(managerDto)));
    }

    @SecurityRequirement(name = "basicauth")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ManagerDto getById(@PathVariable("id") long id) {
        return converter.toDto((managerService.getById(id)));
    }

    @SecurityRequirement(name = "basicauth")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public void deleteById(@PathVariable("id") long id) {
        managerService.deleteById(id);
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