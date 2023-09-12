package org.example.repository;

import org.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT acc FROM Account acc WHERE acc.client.firstName = :firstname AND acc.client.lastName = :lastName")
    List<Account> search(@Param("firstname") String firstName, @Param("lastName") String lastName);
}