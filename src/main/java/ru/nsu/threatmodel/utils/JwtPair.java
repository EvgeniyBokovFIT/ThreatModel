package ru.nsu.threatmodel.utils;

public record JwtPair(
        String accessToken,
        String refreshToken
) {
}
