package ru.nsu.threatmodel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.AbbreviationDecodingDto;
import ru.nsu.threatmodel.repository.AbbreviationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AbbreviationService {

    private final AbbreviationRepository abbreviationRepository;

    public List<AbbreviationDecodingDto> getDecoding(String abbreviation) {
        return abbreviationRepository.findByAbbreviationStartsWithIgnoreCase(abbreviation)
                .stream()
                .map(a -> new AbbreviationDecodingDto(a.getAbbreviation(), a.getDecoding()))
                .collect(Collectors.toList());
    }
}
