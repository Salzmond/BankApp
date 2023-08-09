package org.example.service;

import org.example.entity.Client;
import org.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> search(String email, String lastName) {
        return clientRepository.search(email, lastName);
    }

    @Override
    public Client create(Client client) {
        Client clientEntity;
        clientEntity = getById((client.getId()));
        if (clientEntity == null) {
            clientEntity = clientRepository.save(client);
        }
        return clientEntity;
    }

    @Override
    public void deleteClientById(long id) {
        clientRepository.deleteById(id);
    }
}