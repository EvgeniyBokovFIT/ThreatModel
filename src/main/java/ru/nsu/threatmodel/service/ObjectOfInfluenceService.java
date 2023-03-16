package ru.nsu.threatmodel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.ObjectOfInfluenceDto;
import ru.nsu.threatmodel.repository.ObjectOfInfluenceRepository;
import ru.nsu.threatmodel.utils.ObjectOfInfluenceMapper;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ObjectOfInfluenceService {
    private final ObjectOfInfluenceRepository objectRepository;

    private final ObjectOfInfluenceMapper objectMapper;

    public Set<ObjectOfInfluenceDto> getObjectWithComponents(String objectName) {
        var objects = objectRepository.findByNameStartsWithIgnoreCase(objectName);

        return objects.stream()
                .map(objectMapper)
                .collect(Collectors.toSet());
    }
}
