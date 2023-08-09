package org.example.service.dtoconverter;


import org.example.entity.Agreement;
import org.example.model.dto.AgreementDto;
import org.springframework.stereotype.Service;

@Service
public class AgreementDtoConverter {

    public AgreementDto toDto(Agreement agreement) {
        if (agreement == null) {
            return null;
        }
        return new AgreementDto(agreement.getId(), agreement.getAccount(),
                agreement.getProduct(), agreement.getInterestRate(),
                agreement.getStatus(), agreement.getSum(),
                agreement.getCreatedAt(), agreement.getUpdatedAt());
    }

    public Agreement toEntity(AgreementDto agreementDto) {
        return new Agreement(agreementDto.getId(), agreementDto.getAccount(),
                agreementDto.getProduct(), agreementDto.getInterestRate(),
                agreementDto.getStatus(), agreementDto.getSum(),
                agreementDto.getCreatedAt(), agreementDto.getUpdatedAt());
    }
}