package ru.nsu.threatmodel.service.info;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.entity.User;
import ru.nsu.threatmodel.entity.info.ThreatModel;
import ru.nsu.threatmodel.exception.UserException;
import ru.nsu.threatmodel.repository.UserRepository;
import ru.nsu.threatmodel.repository.info.ThreatModelRepository;
import ru.nsu.threatmodel.utils.JwtUtils;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ThreatModelRepository modelRepository;
    private final UserRepository userRepository;

    public void createNewModel(String accessToken, String modelName) {
        Long userId = JwtUtils.getUserIdByAccessToken(accessToken);
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserException("Пользователь не найден. ID:" + userId));

        ThreatModel model = new ThreatModel();
        model.setName(modelName);
        model.setUser(user);
        modelRepository.save(model);
    }
}
