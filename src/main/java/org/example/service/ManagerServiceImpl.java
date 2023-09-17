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
    public Manager create(Manager manager) {
        Manager managerEntity = managerRepository.findByFirstNameAndLastName(manager.getFirstName(), manager.getLastName());
        if (managerEntity != null) {
            throw new IllegalArgumentException("This manager already exists in system");
        }
        return managerRepository.save(manager);
    }

    @Override
    public void deleteById(long id) {
        managerRepository.delete(getById(id));
    }
}