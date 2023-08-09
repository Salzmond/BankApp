package org.example.service;

import org.example.entity.Agreement;
import org.example.entity.Product;
import org.example.repository.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgreementServiceImpl implements AgreementService {

    @Autowired
    private AgreementRepository agreementRepository;

    @Override
    public List<Agreement> getAll() {
        return agreementRepository.findAll();
    }

    @Override
    public Agreement getById(long id) {
        return agreementRepository.getReferenceById(id);
    }

    @Override
    public List<Agreement> search(Product product, int status) {
        return agreementRepository.search(product, status);
    }

    @Override
    public Agreement create(Agreement agreement) {
        Agreement agreementEntity;
        agreementEntity = getById(agreement.getId());
        if (agreementEntity == null) {
            agreementEntity = agreementRepository.save(agreement);
        }

        return agreementEntity;
    }

    @Override
    public void deleteAgreementById(long id) {
        agreementRepository.deleteById(id);
    }
}