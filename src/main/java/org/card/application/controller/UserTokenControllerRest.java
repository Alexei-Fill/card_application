package org.card.application.controller;

import org.card.application.entity.UserToken;
import org.card.application.service.impl.UserTokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ca/userToken")
public class UserTokenControllerRest {

    @Autowired
    UserTokenServiceImpl userTokenService;

    @GetMapping
    public List<UserToken> findAll(){
        return userTokenService.findAll();
    }

    @PostMapping("/search")
    public UserToken findByLogin(@RequestBody String token) {
        return userTokenService.findUserTokenByToken(token);
    }

    @PostMapping
    public UserToken save (@RequestBody UserToken userToken){
        return userTokenService.saveOrUpdate(userToken);
    }

    @PutMapping
    public UserToken update (@RequestBody UserToken userToken){
        return userTokenService.saveOrUpdate(userToken);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        userTokenService.delete(id);
    }
}
