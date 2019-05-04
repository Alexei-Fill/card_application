package org.card.application.controller;

import org.card.application.entity.User;
import org.card.application.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ca/user")
public class UserControllerRest {

    @Autowired
    UserServiceImpl userService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

    @PostMapping("/search")
    public User findByLogin(@RequestBody String login) {
        return userService.findUserByLogin(login);
    }

    @Secured(value = "isAnonymous()")
    @PostMapping
    public User save (@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping
    public User update (@RequestBody User user){
        return userService.saveOrUpdate(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        userService.delete(id);
    }
}
