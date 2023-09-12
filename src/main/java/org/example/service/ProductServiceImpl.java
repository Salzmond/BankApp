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
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Product with id %d not found", id)));
    }

    @Override
    public List<Product> search(String name, CurrencyCode currencyCode) {
        List<Product> products = productRepository.search(name, currencyCode);
        if (products.isEmpty()) {
            throw new IllegalArgumentException("No product found");
        }
        return products;
    }

    @Override
    public Product create(Product product) {
        Product productEntity = search(product.getName(), product.getCurrencyCode()).get(0);
        if (productEntity != null) {
            throw new IllegalStateException("This product already exists");
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(long id) {
        productRepository.delete(getById(id));
    }
}