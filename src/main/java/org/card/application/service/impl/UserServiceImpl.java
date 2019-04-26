package org.card.application.service.impl;

import org.card.application.entity.User;
import org.card.application.repository.UserRepository;
import org.card.application.service.BaseService;
import org.card.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements BaseService<User, Long>, UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User update(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login).get();
    }
}
