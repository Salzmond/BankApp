package org.example.service;

import org.example.entity.Agreement;
import org.example.entity.Product;

import java.util.List;

public interface AgreementService {

    List<Agreement> getAll();

    Agreement getById(long id);

    Agreement create(Agreement agreement);

    void deleteAgreementById(long id);
}