package ru.nsu.threatmodel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.AbbreviationDto;
import ru.nsu.threatmodel.repository.AbbreviationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AbbreviationService {

    private final AbbreviationRepository abbreviationRepository;

    public List<AbbreviationDto> getDecoding(String abbreviation) {
        return abbreviationRepository.findByAbbreviationStartsWithIgnoreCase(abbreviation)
                .stream()
                .map(a -> new AbbreviationDto(a.getAbbreviation(), a.getDecoding()))
                .collect(Collectors.toList());
    }
}
