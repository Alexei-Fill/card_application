package org.card.application.controller;

import org.card.application.entity.User;
import org.card.application.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControllerRest {

    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public List<User> findAll(){
        return userService.findAll();
    }

//    @PostMapping("search")
//    public User findByLogin(@RequestBody String login) {
//        return userService.loadUserByUsername(login);
//    }

    @PostMapping
    public User save (@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping
    public User update (@RequestBody User user){
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        userService.delete(id);
    }
}
