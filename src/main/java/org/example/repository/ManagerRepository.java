package org.example.repository;

import org.example.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Manager findByFirstNameAndLastName(String firstName, String lastName);
}