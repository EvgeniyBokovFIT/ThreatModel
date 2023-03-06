package ru.nsu.threatmodel.dto;

public record UserDto(
    Long id,
    String login,
    String createdAt
) {
}
