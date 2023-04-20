package ru.nsu.threatmodel.service.info;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.DefinitionDto;
import ru.nsu.threatmodel.entity.info.ModelDefinition;
import ru.nsu.threatmodel.entity.info.ThreatModel;
import ru.nsu.threatmodel.exception.ModelException;
import ru.nsu.threatmodel.repository.info.ModelDefinitionRepository;
import ru.nsu.threatmodel.repository.info.ThreatModelRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelDefinitionService {
    private final ModelDefinitionRepository definitionRepository;
    private final ThreatModelRepository modelRepository;

    public void addDefinitionsToModel(List<DefinitionDto> definitionsDto, Long modelId) {
        ThreatModel model = modelRepository.findById(modelId).
                orElseThrow(() -> new ModelException("Модель не найдена. ID:" + modelId));

        definitionsDto
                .forEach(definitionDto -> {
                    ModelDefinition definition = new ModelDefinition();
                    definition.setDefinition(definitionDto.definition());
                    definition.setMeaning(definitionDto.meaning());
                    definition.setThreatModel(model);
                    definitionRepository.save(definition);
                });
    }
}
