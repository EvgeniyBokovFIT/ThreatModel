package ru.nsu.threatmodel.dto;

public record LoginRequest(
        String login,
        String password
) {
}
