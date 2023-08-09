package org.example.service.dtoconverter;

import org.example.entity.Manager;
import org.example.model.dto.ManagerDto;
import org.springframework.stereotype.Service;

@Service
public class ManagerDtoConverter {

    public ManagerDto toDto(Manager manager) {
        if (manager == null) {
            return null;
        }
        return new ManagerDto(manager.getId(), manager.getFirstName(),
                manager.getLastName(), manager.getStatus(), manager.getCreatedAt(),
                manager.getUpdatedAt());
    }

    public Manager toEntity(ManagerDto managerDto) {
        return new Manager(managerDto.getId(), managerDto.getFirstName(),
                managerDto.getLastName(), managerDto.getStatus(), managerDto.getCreatedAt(),
                managerDto.getUpdatedAt());
    }
}