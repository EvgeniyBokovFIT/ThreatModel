package ru.nsu.threatmodel.utils;

import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.DefinitionDto;
import ru.nsu.threatmodel.entity.Definition;

import java.util.function.Function;

@Service
public class DefinitionMapper implements Function<Definition, DefinitionDto> {
    @Override
    public DefinitionDto apply(Definition definition) {
        return new DefinitionDto(
                definition.getDefinition(),
                definition.getMeaning());
    }
}
