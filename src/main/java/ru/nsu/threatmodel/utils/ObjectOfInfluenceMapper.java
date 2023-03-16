package ru.nsu.threatmodel.utils;

import org.springframework.stereotype.Service;
import ru.nsu.threatmodel.dto.ObjectOfInfluenceDto;
import ru.nsu.threatmodel.entity.Component;
import ru.nsu.threatmodel.entity.ObjectOfInfluence;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ObjectOfInfluenceMapper implements Function<ObjectOfInfluence, ObjectOfInfluenceDto> {

    @Override
    public ObjectOfInfluenceDto apply(ObjectOfInfluence object) {
        var componentsNames = object.getComponents()
                .stream()
                .map(Component::getName)
                .collect(Collectors.toSet());

        return new ObjectOfInfluenceDto(object.getName(), componentsNames);
    }
}
