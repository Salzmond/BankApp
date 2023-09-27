package org.example.service;

import org.example.entity.UserData;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserData create(UserData userData) {
        if (userRepository.findById(userData.getLogin()).isPresent()) {
            throw new EntityExistsException(String.format("User with login %s already exists",
                    userData.getLogin()));
        }
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        return userRepository.save(userData);
    }

    @Override
    public Optional<UserData> findByLogin(String login) {
        return userRepository.findById(login);
    }

    @Override
    public String getCurrentUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public boolean isAuthorize(String login) {
        return getCurrentUserLogin().equals(login);
    }
}