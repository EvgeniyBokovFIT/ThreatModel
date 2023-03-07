package ru.nsu.threatmodel.dto;

import java.util.Set;

public record OperationsOnDataDto(
        Set<String> operations
) {
}
