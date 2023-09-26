package org.example.service;

import org.example.entity.UserData;

public interface AuthService {

    UserData create(UserData userData);

    UserData getCurrentUser();

    boolean isAuthorize(String clientLogin);
}
