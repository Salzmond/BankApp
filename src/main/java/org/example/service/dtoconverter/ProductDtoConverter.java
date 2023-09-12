package org.example.service.dtoconverter;

import org.example.entity.Product;
import org.example.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDtoConverter implements Converter<ProductDto, Product> {

    @Autowired
    private ManagerDtoConverter managerDtoConverter;

    public ProductDto toDto(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDto(managerDtoConverter.toDto(product.getManager()), product.getName(),
                product.getStatus(), product.getCurrencyCode(), product.getInterestRate(),
                product.getProductLimit(), product.getCreatedAt(), product.getUpdatedAt());
    }

    public Product toEntity(ProductDto productDto) {
        return new Product(productDto.getId(),
                productDto.getManager() == null ?
                        managerDtoConverter.toEntity(productDto.getManager()) : null,
                productDto.getName(),
                productDto.getStatus(), productDto.getCurrencyCode(), productDto.getInterestRate(),
                productDto.getProductLimit());
    }
}