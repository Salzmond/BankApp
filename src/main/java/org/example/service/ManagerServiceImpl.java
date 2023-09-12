package org.example.service;

import org.example.entity.Manager;
import org.example.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public List<Manager> getAll() {
        return managerRepository.findAll();
    }

    @Override
    public Manager getById(long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Manager with id %d not exists", id)));
    }

    @Override
    public List<Manager> search(String firstName, String lastName) {
        List<Manager> managers = managerRepository.search(firstName, lastName);
        if (managers.isEmpty()) {
            throw new IllegalArgumentException("No manager found");
        }
        return managers;
    }

    @Override
    public Manager create(Manager manager) {
        Manager managerEntity = search(manager.getFirstName(), manager.getLastName()).get(0);
        if (managerEntity != null) {
            throw new IllegalStateException("This manager already exists in system");
        }
        return managerRepository.save(manager);
    }

    @Override
    public void deleteManagerById(long id) {
        managerRepository.delete(getById(id));
    }
}