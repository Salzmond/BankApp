package org.example.repository;

import org.example.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findByFirstNameAndLastName(String firstName, String lastName);
}