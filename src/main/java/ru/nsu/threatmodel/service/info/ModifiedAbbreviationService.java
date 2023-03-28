package ru.nsu.threatmodel.service.info;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.AbbreviationDto;
import ru.nsu.threatmodel.entity.info.AbbreviationModified;
import ru.nsu.threatmodel.entity.info.ThreatModel;
import ru.nsu.threatmodel.exception.ModelException;
import ru.nsu.threatmodel.repository.info.ModifiedAbbreviationRepository;
import ru.nsu.threatmodel.repository.info.ThreatModelRepository;

@Service
@RequiredArgsConstructor
public class ModifiedAbbreviationService {

    private final ModifiedAbbreviationRepository abbreviationRepository;
    private final ThreatModelRepository modelRepository;

    public void addAbbreviationToModel(AbbreviationDto abbreviationDto, Long modelId) {
        AbbreviationModified abbreviation = new AbbreviationModified();
        abbreviation.setAbbreviation(abbreviationDto.abbreviation());
        abbreviation.setDecoding(abbreviationDto.decoding());

        ThreatModel model = modelRepository.findById(modelId).
                orElseThrow(() -> new ModelException("Модель не найдена. ID:" + modelId));

        abbreviation.setThreatModel(model);
        abbreviationRepository.save(abbreviation);
    }
}
