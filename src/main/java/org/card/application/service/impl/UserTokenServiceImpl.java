package org.card.application.service.impl;

import org.card.application.entity.UserToken;
import org.card.application.repository.UserTokenRepository;
import org.card.application.service.BaseService;
import org.card.application.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTokenServiceImpl implements BaseService<UserToken, Long>, UserTokenService {

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Override
    public UserToken findById(Long id) {
        return userTokenRepository.getOne(id);
    }

    @Override
    public List<UserToken> findAll() {
        return userTokenRepository.findAll();
    }

    @Override
    public UserToken save(UserToken entity) {
        return userTokenRepository.save(entity);
    }

    @Override
    public UserToken update(UserToken entity) {
        return userTokenRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        userTokenRepository.deleteById(id);
    }

    @Override
    public UserToken findUserTokenByToken(String token) {
        return userTokenRepository.findUserTokenByToken(token).get();
    }
}
