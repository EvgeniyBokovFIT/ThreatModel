package ru.nsu.threatmodel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.DefinitionDto;
import ru.nsu.threatmodel.repository.DefinitionRepository;
import ru.nsu.threatmodel.utils.DefinitionMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefinitionService {
    private final DefinitionRepository definitionRepository;
    private final DefinitionMapper definitionMapper;

    public List<DefinitionDto> getMeaning(String definition) {
        return definitionRepository.findByDefinitionStartsWithIgnoreCase(definition)
                .stream()
                .map(definitionMapper)
                .collect(Collectors.toList());
    }
}
