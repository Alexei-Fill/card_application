package org.card.application.service;

import org.card.application.entity.UserToken;

public interface UserTokenService {

    UserToken findUserTokenByToken (String token);
}
