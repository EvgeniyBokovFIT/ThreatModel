package ru.nsu.threatmodel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.RoleDto;
import ru.nsu.threatmodel.repository.RoleRepository;
import ru.nsu.threatmodel.utils.RoleMapper;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public Set<RoleDto> getRolesWithPermissions(String roleName) {
        var roles = roleRepository.findByNameStartsWithIgnoreCase(roleName);


        return roles.stream()
                .map(roleMapper)
                .collect(Collectors.toSet());
    }
}
