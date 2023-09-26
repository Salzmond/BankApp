package org.example.service;

import org.example.entity.UserData;
import org.example.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserData create(UserData userData) {
        userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        return authRepository.save(userData);
    }

    @Override
    public UserData getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return authRepository.getByLogin(userName);
    }

    @Override
    public boolean isAuthorize(String clientLogin) {
        return getCurrentUser().getLogin().equals(clientLogin);
    }
}