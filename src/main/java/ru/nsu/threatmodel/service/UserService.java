package ru.nsu.threatmodel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.RegistrationRequest;
import ru.nsu.threatmodel.dto.UserDto;
import ru.nsu.threatmodel.entity.User;
import ru.nsu.threatmodel.exception.RegistrationException;
import ru.nsu.threatmodel.repository.UserRepository;
import ru.nsu.threatmodel.utils.PasswordGenerationUtils;
import ru.nsu.threatmodel.utils.UserMapper;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto registerUser(RegistrationRequest registrationRequest) {

        checkLoginDoesNotExists(registrationRequest.login());

        var salt = PasswordGenerationUtils.generateSalt();
        var hashedPassword = PasswordGenerationUtils
                .hashPassword(registrationRequest.password(), salt);

        var user = new User();
        user.setLogin(registrationRequest.login());
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        user.setCreatedAt(LocalDateTime.now());

        var savedUser = userRepository.save(user);

        return userMapper.apply(savedUser);
    }

    private void checkLoginDoesNotExists(String login) {
        userRepository.getByLogin(login)
                .ifPresent(user ->
                        {throw new RegistrationException(
                                "Пользователь с таким логином уже существует"); }
                );
    }
}
