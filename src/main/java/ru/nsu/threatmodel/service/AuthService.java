package ru.nsu.threatmodel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.LoginRequest;
import ru.nsu.threatmodel.dto.LoginResponse;
import ru.nsu.threatmodel.entity.User;
import ru.nsu.threatmodel.exception.AuthException;
import ru.nsu.threatmodel.repository.UserRepository;
import ru.nsu.threatmodel.utils.CookieUtils;
import ru.nsu.threatmodel.utils.JwtUtils;
import ru.nsu.threatmodel.utils.PasswordUtils;
import ru.nsu.threatmodel.utils.UserMapper;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final String AUTH_EXCEPTION_MESSAGE = "Неверный логин или пароль";

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public LoginResponse login(LoginRequest loginRequest) {

        var user = checkUser(loginRequest);

        var jwtPair = JwtUtils.generateTokenPair(user);
        user.setRefreshToken(jwtPair.refreshToken());

        return new LoginResponse(jwtPair, userMapper.apply(user));
    }

    public void logout(HttpServletResponse response) {
        CookieUtils.deleteAuthCookies(response);
    }

    private User checkUser(LoginRequest loginRequest) {
        var user = userRepository.getByLogin(loginRequest.login())
                .orElseThrow(() -> new AuthException(AUTH_EXCEPTION_MESSAGE));


        if(!PasswordUtils.isEnteredPasswordMatchesUserPassword(loginRequest.password(),
                    user.getPassword(), user.getSalt())) {
            throw new AuthException(AUTH_EXCEPTION_MESSAGE);
        }

        return user;
    }
}
