package org.card.application.securityConfig;

import org.card.application.entity.UserDetail;
import org.card.application.entity.UserToken;
import org.card.application.service.impl.UserServiceImpl;
import org.card.application.service.impl.UserTokenServiceImpl;
import org.card.application.util.TokenCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Component
public class SavedRequestAwareSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    UserTokenServiceImpl userTokenServiceImpl;
    @Autowired
    UserServiceImpl userServiceImpl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String username = ((UserDetail) authentication.getPrincipal()).getUsername();
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[50];
        random.nextBytes(bytes);
        String token = bytes.toString();
        UserToken userToken = new UserToken(0, token, LocalDateTime.now().plusMinutes(30l), userServiceImpl.findUserByLogin(username));
        userTokenServiceImpl.saveOrUpdate(userToken);
        response.addCookie(TokenCookie.createTokenCookie(token));

    }
}