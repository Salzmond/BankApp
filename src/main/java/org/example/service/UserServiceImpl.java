package org.example.service;

import org.example.entity.UserData;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserData create(UserData userData) {
        if (userRepository.findById(userData.getLogin()).isPresent()) {
            throw new EntityExistsException(String.format("User with login %s already exists",
                    userData.getLogin()));
        }
        return userRepository.save(userData);
    }

    @Override
    public Optional<UserData> findByLogin(String login) {
        return userRepository.findById(login);
    }
}