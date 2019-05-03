package org.card.application.service.impl;

import org.card.application.entity.User;
import org.card.application.entity.UserDetail;
import org.card.application.repository.UserRepository;
import org.card.application.service.BaseService;
import org.card.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.card.application.entity.cardEnum.UserRole.ROLE_USER;

@Service
public class UserServiceImpl implements BaseService<User, Long>, UserService, UserDetailsService {

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
        entity.setRole(ROLE_USER);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        if (username != null && !username.isEmpty()) {
            user = userRepository.findUserByLogin(username).get();
        }
        if (user != null)
            return new UserDetail(user);
        else
            throw  new UsernameNotFoundException("User not found :" + username);
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login).get();
    }
}
