package org.example.controller;

import org.example.model.dto.ManagerDto;
import org.example.service.ManagerService;
import org.example.service.dtoconverter.ManagerDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ManagerDto> getAll() {
        return managerService.getAll().stream()
                .map(manager -> converter.toDto(manager)).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ManagerDto create(@RequestBody ManagerDto managerDto) {
        return converter.toDto(managerService.create(converter.toEntity(managerDto)));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ManagerDto getById(@PathVariable("id") long id) {
        return converter.toDto((managerService.getById(id)));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") long id) {
        managerService.deleteManagerById(id);
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