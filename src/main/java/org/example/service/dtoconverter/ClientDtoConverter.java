package org.example.service.dtoconverter;

import org.example.entity.Client;
import org.example.model.dto.ClientDto;
import org.springframework.stereotype.Service;

@Service
public class ClientDtoConverter {

    public ClientDto toDto(Client client) {
        if (client == null) {
            return null;
        }
        return new ClientDto(client.getId(), client.getManager(), client.getStatus(),
                client.getTaxCode(), client.getFirstName(), client.getLastName(),
                client.getEmail(), client.getAddress(), client.getPhone(), client.getCreatedAt(),
                client.getUpdatedAt());
    }

    public Client toEntity(ClientDto clientDto) {
        return new Client(clientDto.getId(), clientDto.getManager(), clientDto.getStatus(),
                clientDto.getTaxCode(), clientDto.getFirstName(), clientDto.getLastName(),
                clientDto.getEmail(), clientDto.getAddress(), clientDto.getPhone(), clientDto.getCreatedAt(),
                clientDto.getUpdatedAt());
    }
}