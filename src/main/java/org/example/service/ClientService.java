package org.example.service;

import org.example.entity.Client;
import org.example.model.dto.ClientUpdateDto;

import java.util.List;

public interface ClientService {

    List<Client> getAll();

    Client getById(long id);

    List<Client> search(String email, String firstName, String lastName);

    Client create(Client client);

    void deleteClientById(long id);

    Client update(long id, Client client);
}