package org.example.service;

import org.example.entity.Manager;
import org.example.repository.ManagerRepository;
import org.junit.jupiter.api.Assertions;
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

    @Test
    void getAll() {
        Manager managerPeter = new Manager(1L, "Peter", "Pen", 50);
        Manager managerMax = new Manager(2L, "Max", "Pen", 55);
        Mockito.when(managerService.getAll()).thenReturn(List.of(managerMax, managerPeter));
        Assertions.assertEquals(2, managerService.getAll().size());
    }

    @Test
    void getByIdWhenManagerExists() {
        Manager managerPeter = new Manager(1L, "Peter", "Pen", 50);
        Mockito.when(repository.findById(managerPeter.getId())).thenReturn(Optional.of(managerPeter));
        Manager managerEntity = managerService.getById(managerPeter.getId());
        Assertions.assertEquals(1, managerEntity.getId());
    }

    @Test
    void getByIdWhenManagerNotExists() {
        Mockito.when(repository.findById(2L)).thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class, () -> managerService.getById(2));
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
        Mockito.when(repository.findByFirstNameAndLastName(managerBeforeCreate.getFirstName(), managerBeforeCreate.getLastName())).thenReturn(managerEntity);
        Assertions.assertThrows(IllegalArgumentException.class, () -> managerService.create(managerBeforeCreate));
    }

    @Test
    void deleteByIdWhenIdNotExists() {
        Mockito.when(repository.findById(2L)).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> managerService.deleteById(2L));
    }
}