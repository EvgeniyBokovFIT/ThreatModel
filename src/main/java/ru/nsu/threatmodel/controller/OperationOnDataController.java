package ru.nsu.threatmodel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.threatmodel.dto.OperationOnDataDto;
import ru.nsu.threatmodel.service.InformationTypeService;

import java.util.Set;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationOnDataController {

    private final InformationTypeService informationTypeService;

    @GetMapping
    public ResponseEntity<Set<OperationOnDataDto>> getOperationsByInformationType(@RequestParam String type) {
        var operations = informationTypeService.findOperationsByInformationType(type);

        return ResponseEntity.ok(operations);
    }
}
