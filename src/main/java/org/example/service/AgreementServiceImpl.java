package org.example.service;

import org.example.entity.Agreement;
import org.example.entity.Product;
import org.example.repository.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
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
        return agreementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Agreement with id %d not found", id)));
    }

    @Override
    public List<Agreement> search(Product product) {
        List<Agreement> agreements = agreementRepository.search(product);
        if(agreements.isEmpty()) {
            throw new EntityNotFoundException("No agreements found");
        }
        return agreements;
    }

    @Override
    public Agreement create(Agreement agreement) {
        Agreement agreementEntity = search(agreement.getProduct()).get(0);
        if (agreementEntity != null) {
            throw new EntityExistsException("This agreement already exists");
        }
        return agreementRepository.save(agreement);
    }

    @Override
    public void deleteAgreementById(long id) {
        agreementRepository.delete(getById(id));
    }
}