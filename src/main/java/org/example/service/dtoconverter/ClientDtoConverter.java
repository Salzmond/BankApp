package org.example.service.dtoconverter;

import org.example.entity.Client;
import org.example.model.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientDtoConverter implements Converter<ClientDto, Client> {

    @Autowired
    private ManagerDtoConverter managerDtoConverter;

    public ClientDto toDto(Client client) {
        return new ClientDto(client.getId(), managerDtoConverter.toDto(client.getManager()),
                client.getStatus(), client.getTaxCode(), client.getFirstName(), client.getLastName(),
                client.getEmail(), client.getAddress(), client.getPhone(), client.getCreatedAt(),
                client.getUpdatedAt());
    }

    public Client toEntity(ClientDto clientDto) {
        return new Client(clientDto.getId(),
                clientDto.getManagerDto() != null ?
                        managerDtoConverter.toEntity(clientDto.getManagerDto()) : null,
                clientDto.getStatus(), clientDto.getTaxCode(), clientDto.getFirstName(), clientDto.getLastName(),
                clientDto.getEmail(), clientDto.getAddress(), clientDto.getPhone());
    }
}