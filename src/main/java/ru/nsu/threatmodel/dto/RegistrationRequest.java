package ru.nsu.threatmodel.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public record RegistrationRequest(
        @NotBlank(message = "Поле \"Логин\" не должно быть пустым")
        @Size(max = 64, message = "Длина логина должна быть до 64 символов")
        String login,

        @NotBlank(message = "Поле \"Пароль\" не должно быть пустым")
        @Size(min = 8, max = 64, message = "Длина пароля должны быть от 8 до 64 символов")
        String password,

        @NotBlank(message = "Поле \"Подтверждение пароля\" не должно быть пустым")
        @Size(min = 8, max = 64, message = "Длина пароля должны быть от 8 до 64 символов")
        String passwordCheck
) {
}
