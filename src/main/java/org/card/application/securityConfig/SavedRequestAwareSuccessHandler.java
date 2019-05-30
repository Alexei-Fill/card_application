package org.card.application.securityConfig;

import org.card.application.entity.UserDetail;
import org.card.application.service.impl.GetTokenService;
import org.card.application.service.impl.UserServiceImpl;
import org.card.application.util.TokenCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SavedRequestAwareSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    GetTokenService getTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String username = ((UserDetail) authentication.getPrincipal()).getUsername();
        String token = getTokenService.getToken(username);
        response.addCookie(TokenCookie.createTokenCookie(token));
    }


}