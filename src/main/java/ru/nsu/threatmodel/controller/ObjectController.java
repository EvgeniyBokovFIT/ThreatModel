package ru.nsu.threatmodel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.threatmodel.dto.ObjectOfInfluenceDto;
import ru.nsu.threatmodel.service.ObjectOfInfluenceService;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/object")
public class ObjectController {
    private final ObjectOfInfluenceService objectService;

    @GetMapping
    public ResponseEntity<Set<ObjectOfInfluenceDto>> getObjectsWithComponents(
            @RequestParam String objectName) {

        return ResponseEntity.ok(objectService.getObjectWithComponents(objectName));
    }
}
