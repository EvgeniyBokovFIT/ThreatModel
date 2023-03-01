package ru.nsu.threatmodel.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    private CookieUtils() {

    }

    public static void addAuthCookies(HttpServletResponse response,
                                      String accessToken, String refreshToken) {
        response.addCookie(createAccessTokenCookie(accessToken, 365 * 24 * 60 * 60));
        response.addCookie(createRefreshTokenCookie(refreshToken, 365 * 24 * 60 * 60));
    }

    public static void deleteAuthCookies(HttpServletResponse response) {
        response.addCookie(createAccessTokenCookie("", 0));
        response.addCookie(createRefreshTokenCookie("", 0));
    }

    private static Cookie createAccessTokenCookie(String accessToken, Integer maxAge) {
        var accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setMaxAge(maxAge);
        accessTokenCookie.setPath("/");
        return accessTokenCookie;
    }

    private static Cookie createRefreshTokenCookie(String refreshToken, Integer maxAge) {
        var refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setMaxAge(maxAge);
        refreshTokenCookie.setPath("/");
        return refreshTokenCookie;
    }
}
