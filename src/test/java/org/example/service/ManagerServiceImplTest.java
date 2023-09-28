package org.example.service;

import org.example.entity.Client;
import org.example.entity.Manager;
import org.example.repository.ManagerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ManagerServiceImplTest {

    @Mock
    private ManagerRepository repository;

    @InjectMocks
    private ManagerServiceImpl managerService;

    private List<Manager> managers;

    @BeforeEach
    public void init() {
        managers = List.of(
                new Manager(1L, "Peter", "Pen", 50),
                new Manager(2L, "Max", "Pen", 55)
        );
    }

    @Test
    void getAll() {
        Mockito.when(repository.findAll()).thenReturn(managers);
        Assertions.assertEquals(2, managerService.getAll().size());
    }

    @Test
    void getByIdWhenManagerExists() {
        Mockito.when(repository.findById(managers.get(0).getId())).thenReturn(Optional.ofNullable(managers.get(0)));
        Assertions.assertEquals(1L, managerService.getById(managers.get(0).getId()).getId());
    }

    @Test
    void getByIdWhenManagerNotExists() {
        Mockito.when(repository.findById(3L)).thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class, () -> managerService.getById(3L));
    }

    @Test
    void createWhenManagerNotExists() {
        Manager managerBeforeCreate = new Manager(null, "Peter", "Pen", 50);
        Mockito.when(repository.findByFirstNameAndLastName(managerBeforeCreate.getFirstName(), managerBeforeCreate.getLastName())).thenReturn(null);
        Manager managerAfterCreate = new Manager(1L, managerBeforeCreate.getFirstName(), managerBeforeCreate.getLastName(), managerBeforeCreate.getStatus());
        Mockito.when(repository.save(managerBeforeCreate)).thenReturn(managerAfterCreate);
        Assertions.assertEquals(1L, managerService.create(managerBeforeCreate).getId());
    }

    @Test
    void createWhenManagerExists() {
        Manager managerBeforeCreate = new Manager(null, "Peter", "Pen", 50);
        Manager managerEntity = new Manager(1L, managerBeforeCreate.getFirstName(), managerBeforeCreate.getLastName(), managerBeforeCreate.getStatus());
        Mockito.when(repository.findByFirstNameAndLastName(managerBeforeCreate.getFirstName(), managerBeforeCreate.getLastName())).thenReturn(Optional.of(managerEntity));
        Assertions.assertThrows(IllegalArgumentException.class, () -> managerService.create(managerBeforeCreate));
    }

    @Test
    void deleteByIdWhenIdNotExists() {
        Mockito.when(repository.findById(2L)).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> managerService.deleteById(2L));
    }
}