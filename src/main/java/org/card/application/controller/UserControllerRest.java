package org.card.application.controller;

import org.card.application.entity.ApplicationUser;
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
    public List<ApplicationUser> findAll(){
        return userService.findAll();
    }

    @PostMapping("/search")
    public ApplicationUser findByLogin(@RequestBody String login) {
        return userService.findUserByLogin(login);
    }

    @Secured(value = "isAnonymous()")
    @PostMapping
    public ApplicationUser save (@RequestBody ApplicationUser applicationUser){
        return userService.saveOrUpdate(applicationUser);
    }

    @PutMapping
    public ApplicationUser update (@RequestBody ApplicationUser applicationUser){
        return userService.saveOrUpdate(applicationUser);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        userService.delete(id);
    }
}
