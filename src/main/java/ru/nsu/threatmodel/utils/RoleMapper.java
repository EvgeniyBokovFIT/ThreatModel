package ru.nsu.threatmodel.utils;

import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.RoleDto;
import ru.nsu.threatmodel.entity.Permission;
import ru.nsu.threatmodel.entity.Role;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RoleMapper implements Function<Role, RoleDto> {
    @Override
    public RoleDto apply(Role role) {
        return new RoleDto(role.getName(), role.getPermissions()
                .stream()
                .map(Permission::getName)
                .collect(Collectors.toSet()));

    }
}
