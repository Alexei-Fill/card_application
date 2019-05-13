package org.card.application.service;

import org.card.application.entity.ApplicationUser;

public interface UserService {
    ApplicationUser findUserByLogin(String login);
}
