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
        return managerRepository.getReferenceById(id);
    }

    @Override
    public List<Manager> search(String firstName, String lastName) {
        return managerRepository.search(firstName, lastName);
    }

    @Override
    public Manager create(Manager manager) {
        Manager managerEntity;
        managerEntity = getById(manager.getId());
        if (managerEntity == null) {
            managerEntity = managerRepository.save(manager);
        }
        return managerEntity;
    }

    @Override
    public void deleteManagerById(long id) {
        managerRepository.deleteById(id);
    }
}