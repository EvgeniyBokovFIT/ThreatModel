package ru.nsu.threatmodel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.OperationsOnDataDto;
import ru.nsu.threatmodel.repository.InformationTypeRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class InformationTypeService {
    private final InformationTypeRepository informationTypeRepository;

    public OperationsOnDataDto findOperationsByInformationType(String type) {
        var optionalInformationType = informationTypeRepository.findByType(type);
        if(optionalInformationType.isEmpty()) {
            return new OperationsOnDataDto(null);
        }

        var informationType = optionalInformationType.get();
        Set<String> operations = new HashSet<>();
        informationType.getOperations()
                .forEach(op -> operations.add(op.getName()));

        return new OperationsOnDataDto(operations);
    }
}
