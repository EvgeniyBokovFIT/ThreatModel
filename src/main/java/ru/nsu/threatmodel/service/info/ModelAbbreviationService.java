package ru.nsu.threatmodel.service.info;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.AbbreviationDto;
import ru.nsu.threatmodel.entity.info.ModelAbbreviation;
import ru.nsu.threatmodel.entity.info.ThreatModel;
import ru.nsu.threatmodel.exception.ModelException;
import ru.nsu.threatmodel.repository.info.ModelAbbreviationRepository;
import ru.nsu.threatmodel.repository.info.ThreatModelRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelAbbreviationService {

    private final ModelAbbreviationRepository abbreviationRepository;
    private final ThreatModelRepository modelRepository;

    public void addAbbreviationsToModel(List<AbbreviationDto> abbreviationsDto, Long modelId) {
        ThreatModel model = modelRepository.findById(modelId).
                orElseThrow(() -> new ModelException("Модель не найдена. ID:" + modelId));

        abbreviationsDto
                .forEach(abbreviationDto -> {
                    ModelAbbreviation abbreviation = new ModelAbbreviation();
                    abbreviation.setAbbreviation(abbreviationDto.abbreviation());
                    abbreviation.setDecoding(abbreviationDto.decoding());
                    abbreviation.setThreatModel(model);
                    abbreviationRepository.save(abbreviation);
                });
    }
}
