package org.example.service.dtoconverter;

import org.example.entity.Client;
import org.example.model.dto.ClientDto;
import org.example.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientDtoConverter implements Converter<ClientDto, Client> {

    @Autowired
    private ManagerDtoConverter managerDtoConverter;

    @Autowired
    private ManagerService managerService;

    public ClientDto toDto(Client client) {
        return new ClientDto(client.getId(), client.getManager().getFirstName(), client.getManager().getLastName(),
                client.getStatus(), client.getTaxCode(), client.getFirstName(), client.getLastName(),
                client.getEmail(), client.getAddress(), client.getPhone());
    }

    public Client toEntity(ClientDto clientDto) {
        return new Client(managerService.findManagerByFirstAndLastName(clientDto.getManagersFirstName(),clientDto.getManagersSecondName()),
                clientDto.getStatus(), clientDto.getTaxCode(), clientDto.getFirstName(), clientDto.getLastName(),
                clientDto.getEmail(), clientDto.getAddress(), clientDto.getPhone());
    }
}