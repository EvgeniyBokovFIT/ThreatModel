package ru.nsu.threatmodel.dto;

import java.util.Set;

public record RoleDto(
        String roleName,
        Set<String> permissions
) {
}
