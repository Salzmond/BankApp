package org.example.service;

import org.example.entity.Manager;
import org.example.model.dto.ManagerDto;
import org.example.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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
                .orElseThrow(() -> new EntityNotFoundException(String.format("Manager with id %d not exists", id)));
    }

    @Override
    public Manager create(Manager manager) {
        Manager managerEntity = managerRepository.findByFirstNameAndLastName(manager.getFirstName(), manager.getLastName());
        if (managerEntity != null) {
            throw new EntityExistsException("This manager already exists in system");
        }
        return managerRepository.save(manager);
    }

    @Override
    public Manager findManagerByFirstAndLastName(String firstName, String lastName) {
        return Optional.of(managerRepository.findByFirstNameAndLastName(firstName, lastName))
                .orElseThrow(() -> new EntityNotFoundException("Manager not found"));
    }

    @Override
    public void deleteById(long id) {
        managerRepository.delete(getById(id));
    }
}