package ru.nsu.threatmodel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.threatmodel.dto.RegistrationRequest;
import ru.nsu.threatmodel.dto.UserDto;
import ru.nsu.threatmodel.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegistrationRequest registrationRequest) {
        var user = userService.registerUser(registrationRequest);

        return ResponseEntity.ok(user);
    }
}
