package ru.nsu.threatmodel.utils;

import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.UserDto;
import ru.nsu.threatmodel.entity.User;

import java.util.function.Function;

@Service
public class UserMapper implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getCreatedAt()
        );
    }
}
