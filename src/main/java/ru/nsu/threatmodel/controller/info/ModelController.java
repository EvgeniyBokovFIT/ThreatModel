package ru.nsu.threatmodel.controller.info;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.threatmodel.dto.AbbreviationDto;
import ru.nsu.threatmodel.dto.ModelCreatingRequest;
import ru.nsu.threatmodel.dto.ModelResponse;
import ru.nsu.threatmodel.service.info.DocumentService;
import ru.nsu.threatmodel.service.info.ModelService;
import ru.nsu.threatmodel.service.info.ModifiedAbbreviationService;

import java.util.List;

@RestController
@RequestMapping("/model")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;
    private final ModifiedAbbreviationService abbreviationService;
    private final DocumentService documentService;

    @PostMapping("/create")
    public ResponseEntity<ModelResponse> createModel(
            @CookieValue String accessToken,
            @RequestBody ModelCreatingRequest modelCreatingrequest) {
        ModelResponse createdModel = modelService.createNewModel(accessToken, modelCreatingrequest.name());
        return ResponseEntity.ok(createdModel);
    }

    @PostMapping("/abbreviation/add")
    public ResponseEntity<?> addAbbreviationsToModel(
            @RequestParam Long modelId,
            @RequestBody List<AbbreviationDto> abbreviationDtoList) {
        abbreviationService.addAbbreviationsToModel(abbreviationDtoList, modelId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/document/create")
    public ResponseEntity<?> getDocument(@RequestParam Long modelId) {
        documentService.createDocument(modelId);
        return ResponseEntity.ok().build();
    }

}
