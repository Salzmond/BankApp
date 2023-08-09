package org.example.repository;

import org.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT acc FROM Account acc WHERE acc.name = :name AND acc.status = :status")
    List<Account> search(@Param("name") String name, @Param("name") int status);
}