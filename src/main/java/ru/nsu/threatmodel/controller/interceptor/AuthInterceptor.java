package ru.nsu.threatmodel.controller.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.nsu.threatmodel.entity.User;
import ru.nsu.threatmodel.exception.AuthException;
import ru.nsu.threatmodel.repository.UserRepository;
import ru.nsu.threatmodel.utils.CookieUtils;
import ru.nsu.threatmodel.utils.JwtUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response, @NonNull Object handler) {
        var cookies = request.getCookies();
        if(cookies == null) {
            throw new AuthException("Пользователь не авторизован");
        }

        var accessTokenCookie = findCookieByName("accessToken", cookies);

        var refreshTokenCookie = findCookieByName("refreshToken", cookies);

        var decodedAccessToken = JwtUtils.parseToken(accessTokenCookie.getValue());

        if(isTokenExpired(decodedAccessToken)) {
            log.info("Access токен пользователя id=" + decodedAccessToken.getSubject() +  "просрочен");

            return updateTokens(response, accessTokenCookie.getValue(), refreshTokenCookie.getValue());
        }

        JwtUtils.verifyToken(accessTokenCookie.getValue());

        return true;
    }

    private Date getNow() {
        return new Date(System.currentTimeMillis());
    }

    private boolean isTokenExpired(DecodedJWT decodedJWT) {
        return decodedJWT.getExpiresAt().before(getNow());
    }

    private void updateJwtPair(User user, HttpServletResponse response) {
        var jwtPair = JwtUtils.generateTokenPair(user);

        user.setRefreshToken(jwtPair.refreshToken());
        userRepository.save(user);

        CookieUtils.addAuthCookies(response, jwtPair.accessToken(), jwtPair.refreshToken());
        log.info("Обновлены токены пользователя " + user.getLogin() + " с id=" + user.getId());
    }

    private Cookie findCookieByName(String cookieName, Cookie[] cookies) {
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(cookieName))
                .findFirst()
                .orElseThrow(() ->
                        new AuthException("Отсутствует обязательный cookie \"" + cookieName + "\""));
    }

    private boolean updateTokens(HttpServletResponse response,
                                 String accessToken, String refreshToken) {
        var userOptional = userRepository.findById(
                JwtUtils.getUserIdByAccessToken(accessToken));

        if(userOptional.isEmpty()) {
            return false;
        }

        var user = userOptional.get();

        if(!user.getRefreshToken().equals(refreshToken)) {
            return false;
        } else {
            updateJwtPair(user, response);
        }
        return true;
    }
}
