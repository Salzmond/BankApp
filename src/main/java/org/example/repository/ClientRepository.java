package org.example.repository;

import org.example.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {

    @Query("SELECT clt FROM Client clt WHERE clt.email = :email AND clt.firstName = :firstName AND clt.lastName = :lastName")
    List<Client> search(@Param("email") String email,
                        @Param("firstName") String firstName,
                        @Param("lastName") String lastName);
}