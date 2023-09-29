package org.example.service;

import org.example.entity.Client;
import org.example.exception.ClientExistsException;
import org.example.exception.ClientNotFoundException;
import org.example.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void getAll() {
        List<Client> clients = List.of(
                new Client("Oleg", "Rylov", "clients2@test.com", "89758 Munich DE", "+497895896"),
                new Client("Anton", "Vouk", "clients1@test.com", "10010 New York USA", "+121258969"));
        Mockito.when(clientRepository.findAll()).thenReturn(clients);
        assertEquals(2, clientService.getAll().size());
    }

    @Test
    void getByIdWhenClientExists() {
        Client client = new Client(1L, "Oleg", "Rylov", "clients2@test.com", "89758 Munich DE", "+497895896");
        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        assertEquals(client.getId(), clientService.getById(1L).getId());
    }

    @Test
    void getByIdWhenClientNotExists() {
        Client client = new Client(1L, "Oleg", "Rylov", "clients2@test.com", "89758 Munich DE", "+497895896");
        Mockito.when(clientRepository.findById(2L)).thenReturn(Optional.ofNullable(null));
        assertThrows(ClientNotFoundException.class, () -> clientService.getById(2L));
    }

    @Test
    void createWhenClientNotExists() {
        Client clientBeforeSave = new Client("Oleg", "Rylov", "clients2@test.com", "89758 Munich DE", "+497895896");
        Client client = new Client(1L, clientBeforeSave.getFirstName(), clientBeforeSave.getLastName(),
                clientBeforeSave.getEmail(), clientBeforeSave.getAddress(), clientBeforeSave.getPhone());
        Mockito.when(clientRepository.save(clientBeforeSave)).thenReturn(client);
        Client clientAfterSave = clientService.create(clientBeforeSave);
        assertEquals(client.getId(), clientAfterSave.getId());
        assertEquals(client.getFirstName(), clientAfterSave.getFirstName());
    }

    @Test
    void createWhenClientExists() {
        Client clientBeforeSave = new Client("Oleg", "Rylov", "clients2@test.com", "89758 Munich DE", "+497895896");
        Client client = new Client(1L, clientBeforeSave.getFirstName(), clientBeforeSave.getLastName(),
                clientBeforeSave.getEmail(), clientBeforeSave.getAddress(), clientBeforeSave.getPhone());
        Mockito.when(clientRepository.findClientByEmail(clientBeforeSave.getEmail())).thenReturn(Optional.of(client));
        assertThrows(ClientExistsException.class, () -> clientService.create(clientBeforeSave));
    }

    @Test
    void deleteById() {
        Client client = new Client(1L, "Oleg", "Rylov", "clients2@test.com", "89758 Munich DE", "+497895896");
        Mockito.when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        clientService.deleteById(client.getId());
        Mockito.verify(clientRepository).delete(client);
    }

    @Test
    void updateWhenClientExists() {
        String login = "clients2@test.com";
        Mockito.when(userService.getCurrentUserLogin()).thenReturn(login);
        Client clientBeforeUpdate = new Client(1L, "Oleg", "Rylov", "clients2@test.com", "89758 Munich DE", "+497895896");
        Mockito.when(clientRepository.findClientByEmail(login)).thenReturn(Optional.of(clientBeforeUpdate));
        Client clientUpdate = new Client("10010 New York USA", "+121258969", "12345");
        Client clientWithUpdate = new Client(1L, "Oleg", "Rylov", "clients2@test.com", "10010 New York USA", "+121258969");

        Mockito.when(clientRepository.save(Mockito.any())).thenReturn(clientWithUpdate);
        assertEquals(clientUpdate.getAddress(), clientService.update(clientUpdate).getAddress());
    }

    @Test
    void updateWhenClientNotExists() {
        String login = "clients2@test.com";
        Client clientUpdate = new Client("10010 New York USA", "+121258969", "12345");
        Mockito.when(userService.getCurrentUserLogin()).thenReturn(login);
        Mockito.when(clientRepository.findClientByEmail(login)).thenReturn(Optional.ofNullable(null));
        assertThrows(ClientNotFoundException.class, () -> clientService.update(clientUpdate));
    }
}