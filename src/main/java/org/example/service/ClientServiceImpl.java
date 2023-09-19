package org.example.service;

import org.example.entity.Client;
import org.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getById(long id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Client with id %d not found", id)));
    }

    @Override
    public Client create(Client client) {
        Client clientEntity = clientRepository.findClientByEmail(client.getEmail());
        if (clientEntity != null) {
            throw new EntityExistsException("This client already exists in system");
        }
        return clientRepository.save(client);
    }


    @Override
    public void deleteById(long id) {
        clientRepository.delete(getById(id));
    }

    @Override
    public Client update(long id, Client client) {
        Client clientEntity = getById(id);
        if(client.getAddress() != null) {
            clientEntity.setAddress(client.getAddress());
        }
        if(client.getPhone() != null) {
            clientEntity.setPhone(client.getPhone());
        }
        clientEntity.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return clientRepository.save(clientEntity);
    }
}