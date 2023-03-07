package ru.nsu.threatmodel.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.threatmodel.dto.DefinitionDto;
import ru.nsu.threatmodel.service.DefinitionService;

import java.util.List;

@RestController
@RequestMapping("/definition")
@RequiredArgsConstructor
@Slf4j
public class DefinitionController {

    private final DefinitionService definitionService;

    @GetMapping
    public ResponseEntity<List<DefinitionDto>> getDefinitionMeaningStartsWith(@RequestParam String definition) {
        return ResponseEntity.ok(definitionService.getMeaning(definition));
    }
}
