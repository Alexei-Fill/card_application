package org.card.application.service.impl;

import org.card.application.entity.ApplicationUser;
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
public class UserServiceImpl implements BaseService<ApplicationUser, Long>, UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ApplicationUser findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<ApplicationUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public ApplicationUser saveOrUpdate(ApplicationUser entity) {
        if (entity.getId() == 0){
            entity.setRole(ROLE_USER);
        }
        return userRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = null;
        if (username != null && !username.isEmpty()) {
            applicationUser = userRepository.findUserByLogin(username).get();
        }
        if (applicationUser != null)
            return new UserDetail(applicationUser);
        else
            throw  new UsernameNotFoundException("ApplicationUser not found :" + username);
    }

    @Override
    public ApplicationUser findUserByLogin(String login) {
        return userRepository.findUserByLogin(login).get();
    }
}
