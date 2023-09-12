package org.example.service;

import org.example.entity.Client;
import org.example.model.dto.ClientUpdateDto;
import org.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                () -> new IllegalArgumentException(String.format("Client with id %d not found", id)));
    }

    @Override
    public List<Client> search(String email, String firstName, String lastName) {
        List<Client> clients = clientRepository.search(email, firstName, lastName);
        if (clients.isEmpty()) {
            throw new IllegalArgumentException("No client found");
        }
        return clients;
    }

    @Override
    public Client create(Client client) {
        List<Client> clients = search(client.getEmail(), client.getFirstName(), client.getLastName());
        if (!clients.isEmpty()) {
            throw new IllegalStateException("This client already exists in system");
        }
        return clientRepository.save(client);
    }


    @Override
    public void deleteClientById(long id) {
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