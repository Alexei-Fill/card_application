package org.card.application.util;

import javax.servlet.http.Cookie;

public class TokenCookie {

    private TokenCookie() {
        throw new IllegalStateException("Not supported. Token must be not null");
    }

    public static Cookie createTokenCookie(String accessToken, int maxAge) {
        Cookie tokenCookie = new Cookie("x-auth-token", accessToken);
        tokenCookie.setDomain("");
        tokenCookie.setPath("/");
        tokenCookie.setMaxAge(maxAge);
        return tokenCookie;
    }

    public static Cookie createTokenCookie(String accessToken) {
        final int STORED_COOKIE_TIME = 30*60;
        return createTokenCookie(accessToken, STORED_COOKIE_TIME );
    }
}
