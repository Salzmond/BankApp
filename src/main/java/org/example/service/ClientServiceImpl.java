package org.example.service;

import org.example.entity.Client;
import org.example.entity.UserData;
import org.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private AuthService authService;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAll() {
        //    UserData currentUser = authService.getCurrentUser();
        return clientRepository.findAll();
    }

    @Override
    public Client getById(long id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Client with id %d not found", id)));
    }

    @Override
    public Client create(Client client) {
        Optional<Client> clientEntity = clientRepository.findClientByEmail(client.getEmail());
        if (clientEntity.isPresent()) {
            throw new EntityExistsException("This client already exists in system");
        }

        client.setEmail(authService.getCurrentUser().getLogin());
        return clientRepository.save(client);
    }


    @Override
    public void deleteById(long id) {
        clientRepository.delete(getById(id));
    }

    @Override
    public Client update(long id, Client client) {
        Client clientEntity = getById(id);
        if (client.getAddress() != null) {
            clientEntity.setAddress(client.getAddress());
        }
        if (client.getPhone() != null) {
            clientEntity.setPhone(client.getPhone());
        }
        clientEntity.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return clientRepository.save(clientEntity);
    }

    @Override
    public Client getCurrent() {
        UserData currentUser = authService.getCurrentUser();
        return clientRepository.findClientByEmail(currentUser.getLogin()).
                orElseThrow(() -> new EntityNotFoundException("Please fill the personal information"));
    }
}