package org.example.service.dtoconverter;

import org.example.entity.Manager;
import org.example.model.dto.ManagerDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ManagerDtoConverter implements Converter<ManagerDto, Manager> {

    public ManagerDto toDto(Manager manager) {
        return new ManagerDto(manager.getId(), manager.getFirstName(),
                manager.getLastName(), manager.getStatus(),
                manager.getClients().stream()
                                .flatMap(client -> Stream.of(client.getFirstName() + " " + client.getLastName())).collect(Collectors.toList()),
                manager.getProducts().stream().
                       map(product -> product.getName()).collect(Collectors.toList()),
                manager.getCreatedAt(),
                manager.getUpdatedAt());
    }

    public Manager toEntity(ManagerDto managerDto) {
        return new Manager(managerDto.getFirstName(),
                managerDto.getLastName(), managerDto.getStatus());
    }
}