package ru.nsu.threatmodel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.threatmodel.dto.RoleDto;
import ru.nsu.threatmodel.service.RoleService;

import java.util.Set;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<Set<RoleDto>> getRoleWithPermissionsByRoleName(@RequestParam String roleName) {
        return ResponseEntity.ok(roleService.getRolesWithPermissions(roleName));
    }
}
