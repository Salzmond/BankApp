package org.example.repository;

import org.example.entity.Product;
import org.example.model.enums.CurrencyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT prod FROM Product prod WHERE prod.name = :name AND prod.currencyCode = :currencyCode")
    List<Product> search(@Param("name") String name, @Param("currencyCode") CurrencyCode currencyCode);
}