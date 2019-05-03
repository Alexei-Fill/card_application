package org.card.application.service;

import org.card.application.entity.User;

public interface UserService {
    User findUserByLogin(String login);
}
