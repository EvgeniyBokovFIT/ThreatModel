package ru.nsu.threatmodel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.OperationOnDataDto;
import ru.nsu.threatmodel.repository.InformationTypeRepository;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InformationTypeService {
    private final InformationTypeRepository informationTypeRepository;

    public Set<OperationOnDataDto> findOperationsByInformationType(String type) {
        var optionalInformationType = informationTypeRepository.findByType(type);
        if(optionalInformationType.isEmpty()) {
            return Collections.emptySet();
        }

        var informationType = optionalInformationType.get();
        return informationType.getOperations()
                .stream()
                .map(op -> new OperationOnDataDto(op.getName()))
                .collect(Collectors.toSet());
    }
}
