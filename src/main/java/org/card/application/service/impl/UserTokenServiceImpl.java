package org.card.application.service.impl;

import org.card.application.entity.UserToken;
import org.card.application.repository.UserTokenRepository;
import org.card.application.service.BaseService;
import org.card.application.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public UserToken saveOrUpdate(UserToken entity) {
//        UserToken userToken = userTokenRepository.findUserTokenByUserId(entity.getApplicationUser().getId()).orElse(new UserToken());
//        if (userToken.getId() != 0) {
//            entity.setId(userToken.getId());
//        }
//        return userTokenRepository.save(entity);
        return null;
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
