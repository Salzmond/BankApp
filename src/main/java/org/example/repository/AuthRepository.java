package org.example.repository;

import org.example.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<UserData,String> {

    UserData getByLogin(String login);
}
