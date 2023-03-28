package ru.nsu.threatmodel.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.threatmodel.dto.AbbreviationDto;
import ru.nsu.threatmodel.service.AbbreviationService;

import java.util.List;

@RestController
@RequestMapping("/abbreviation")
@AllArgsConstructor
public class AbbreviationController {
    private AbbreviationService service;
    @GetMapping
    public ResponseEntity<List<AbbreviationDto>> getDecodingOfAbbreviation(@RequestParam String abbreviation) {
        return ResponseEntity.ok(service.getDecoding(abbreviation));
    }
}
