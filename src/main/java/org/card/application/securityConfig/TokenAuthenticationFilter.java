package org.card.application.securityConfig;

import org.card.application.entity.UserToken;
import org.card.application.service.impl.UserTokenServiceImpl;
import org.card.application.util.TokenCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import java.io.IOException;
import java.util.Arrays;

import static org.card.application.util.Const.*;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private UserTokenServiceImpl userTokenService;

    public TokenAuthenticationFilter() throws NotSupportedException {
        throw new NotSupportedException();
    }

    public TokenAuthenticationFilter(UserTokenServiceImpl userTokenService) {
        this.userTokenService = userTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        Cookie cookie = WebUtils.getCookie(httpServletRequest, "x-auth-token");
        String accessToken = null;
        if (cookie != null) {
            accessToken = cookie.getValue();
            httpServletResponse.addCookie(TokenCookie.createTokenCookie(accessToken));
        }
        if (accessToken != null ) {
            UserToken userTokenEntity = userTokenService.findUserTokenByToken(accessToken);

            final User user = new User(
                    userTokenEntity.getUser().getLogin(),
                    userTokenEntity.getUser().getPassword(),
                    ENABLED,
                    ACCOUNT_NON_EXPIRED,
                    CREDENTIALS_NON_EXPIRED,
                    ACCOUNT_NON_LOCKED,
                    Arrays.asList(new SimpleGrantedAuthority(userTokenEntity.getUser().getRole().toString())));
            final UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
