package ru.nsu.threatmodel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.repository.UserRepository;
import ru.nsu.threatmodel.utils.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
}
