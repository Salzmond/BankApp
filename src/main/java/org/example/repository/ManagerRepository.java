package org.example.repository;

import org.example.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @Query("SELECT mng FROM Manager mng WHERE mng.firstName = :firstName AND mng.lastName = :lastName")
    List<Manager> search(@Param("firstName") String firstName, @Param("lastName") String lastName);
}