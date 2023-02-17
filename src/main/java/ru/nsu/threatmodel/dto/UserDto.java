package ru.nsu.threatmodel.dto;

import java.time.LocalDateTime;

public record UserDto(
    Long id,
    String login,
    LocalDateTime createdAt
) {
}
