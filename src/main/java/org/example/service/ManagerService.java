package org.example.service;

import org.example.entity.Manager;

import java.util.List;

public interface ManagerService {
    List<Manager> getAll();

    Manager getById(long id);

    List<Manager> search(String firstName, String lastName);

    Manager create(Manager manager);

    void deleteManagerById(long id);
}