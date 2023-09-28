package org.example.service;

import org.example.entity.Manager;
import org.example.model.dto.ManagerDto;

import java.util.List;

public interface ManagerService {

    List<Manager> getAll();

    Manager getById(long id);

    Manager create(Manager manager);

    void deleteById(long id);
}