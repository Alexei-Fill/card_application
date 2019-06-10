package org.card.application.securityConfig;

import com.google.gson.Gson;
import org.card.application.service.impl.UserServiceImpl;
import org.card.application.util.AuthenticationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.springframework.http.HttpMethod.POST;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        AuthenticationData authenticationData = this.getLoginRequest(request);
        if (isNotPost(request) && isNull(authenticationData)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        UsernamePasswordAuthenticationToken authenticationRequest =
                new UsernamePasswordAuthenticationToken(authenticationData.getUsername(), authenticationData.getPassword());
        setDetails(request, authenticationRequest);
        return this.getAuthenticationManager().authenticate(authenticationRequest);
    }

    private AuthenticationData getLoginRequest(HttpServletRequest request) {
        AuthenticationData authenticationData = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {

            Gson gson = new Gson();
            authenticationData = gson.fromJson(reader, AuthenticationData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(authenticationData);
        if (authenticationData == null) {
            authenticationData = new AuthenticationData();
        }
        return authenticationData;
    }

    private boolean isNotPost(HttpServletRequest request) {
        return !request.getMethod().equals(POST);
    }

    private boolean isNull(AuthenticationData authenticationData) {
        return userServiceImpl.findUserByLogin(authenticationData.getUsername()) == null;
    }
}
