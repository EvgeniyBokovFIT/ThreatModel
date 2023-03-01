package ru.nsu.threatmodel.dto;

import ru.nsu.threatmodel.utils.JwtPair;

public record LoginResponse (
        JwtPair jwtPair,
        UserDto userDto
){

}
