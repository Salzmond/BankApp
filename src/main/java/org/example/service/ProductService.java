package org.example.service;

import org.example.entity.Account;
import org.example.entity.Product;
import org.example.model.enums.CurrencyCode;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product getById(long id);

    List<Product> search(String name, CurrencyCode currencyCode);

    Product create(Product product);

    void deleteProductById(long id);
}