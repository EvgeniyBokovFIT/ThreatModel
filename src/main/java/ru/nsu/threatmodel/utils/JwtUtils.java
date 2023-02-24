package ru.nsu.threatmodel.utils;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import ru.nsu.threatmodel.entity.User;
import com.auth0.jwt.JWT;
import ru.nsu.threatmodel.exception.AuthException;

import java.util.Date;
public class JwtUtils {

    private static final Long JWT_AUTH_TOKEN_VALIDITY = 600_000_000_000L; // 10 минут
    private static final Long JWT_REFRESH_TOKEN_VALIDITY = 604_800_000L; // 1 неделя
    private static final String SECRET = "secretPhrase";

    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET);

    private JwtUtils() {

    }

    public static JwtPair generateTokenPair(User user) {
        var accessToken = JWT.create()
                .withSubject(user.getId().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_AUTH_TOKEN_VALIDITY))
                .sign(algorithm);

        var refreshToken = JWT.create()
                .withSubject(user.getId().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_REFRESH_TOKEN_VALIDITY))
                .sign(algorithm);

        return new JwtPair(accessToken, refreshToken);
    }

    public static DecodedJWT parseToken(String token) {
        return new JWT().decodeJwt(token);
    }

    public static DecodedJWT verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new AuthException("Переданный JWT токен не валиден");
        }
    }

    public Long getUserIdByAccessToken(String accessToken) {
        var decodedAccessTokenJwt = parseToken(accessToken);
        return Long.parseLong(decodedAccessTokenJwt.getSubject());
    }
}
