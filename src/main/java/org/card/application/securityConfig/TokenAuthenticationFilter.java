package org.card.application.securityConfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.card.application.entity.UserDetail;
import org.card.application.service.impl.UserServiceImpl;
import org.card.application.util.TokenCookie;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.NotSupportedException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;

import static org.card.application.util.Const.*;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private UserServiceImpl userService;

    public TokenAuthenticationFilter() throws NotSupportedException {
        throw new NotSupportedException();
    }

    public TokenAuthenticationFilter(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String key = "topSecretKey";
        Cookie cookie = WebUtils.getCookie(httpServletRequest, "x-auth-token");
        String accessToken = null;
        if (cookie != null) {
            accessToken = cookie.getValue();
            httpServletResponse.addCookie(TokenCookie.createTokenCookie(accessToken));
        }
        if (accessToken != null ) {
            DefaultClaims claims;
            try {
                claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(accessToken).getBody();
            } catch (Exception ex) {
                throw new AuthenticationServiceException("Token corrupted");
            }
            if (claims.get("token_expiration_date", String.class) == null)
                throw new AuthenticationServiceException("Invalid token");
            LocalDateTime expiredDate = LocalDateTime.parse(claims.get("token_expiration_date", String.class));
            if (expiredDate.isAfter(LocalDateTime.now())) {
            SecurityContextHolder.getContext().setAuthentication(buildFullTokenAuthentication(claims));
            }
            else {
                throw new AuthenticationServiceException("Token expired date error");
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private UsernamePasswordAuthenticationToken buildFullTokenAuthentication(DefaultClaims claims) {
        UserDetail userDetails = (UserDetail) userService.loadUserByUsername(claims.get("username", String.class));
        if (userDetails.isEnabled() && userDetails.getUser().getId() == claims.get("userId", Long.class)) {
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            final User user = new User(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    ENABLED,
                    ACCOUNT_NON_EXPIRED,
                    CREDENTIALS_NON_EXPIRED,
                    ACCOUNT_NON_LOCKED,
                    userDetails.getAuthorities());
            final UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return authentication;
        } else {
            throw new AuthenticationServiceException("ApplicationUser disabled");
        }
    }
}
