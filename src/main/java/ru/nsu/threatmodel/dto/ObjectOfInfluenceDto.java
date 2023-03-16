package ru.nsu.threatmodel.dto;

import java.util.Set;

public record ObjectOfInfluenceDto(
        String name,
        Set<String> components
) {
}
