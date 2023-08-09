package org.example.repository;

import org.example.entity.Agreement;
import org.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {

    @Query("SELECT agg FROM Agreement agg WHERE agg.product = :product AND agg.status = :status")
    List<Agreement> search(@Param("product")Product product, @Param("status") int status);
}