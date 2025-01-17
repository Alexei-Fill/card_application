package org.card.application.service.impl;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.card.application.entity.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Service
public class GetTokenService {
    @Autowired
    UserServiceImpl userServiceImpl;

    public String getToken (String username) {
        if(username == null){
            return null;
        }
        ApplicationUser applicationUser = userServiceImpl.findUserByLogin(username);
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("userId", applicationUser.getId());
        tokenData.put("username", applicationUser.getLogin());
        tokenData.put("token_expiration_date", LocalDateTime.now().plusMinutes(03).toString());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setClaims(tokenData);
        String key = "topSecretKey";
        String token = jwtBuilder.signWith(HS512, key).compact();
        return token;
    }
}
