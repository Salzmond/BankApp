package org.example.service;

import org.example.entity.Product;
import org.example.model.enums.CurrencyCode;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(long id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public List<Product> search(String name, CurrencyCode currencyCode) {
        return productRepository.search(name, currencyCode);
    }

    @Override
    public Product create(Product product) {
        Product productEntity;
        productEntity = getById(product.getId());
        if (productEntity == null) {
            productEntity = productRepository.save(product);
        }
        return productEntity;
    }

    @Override
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }
}