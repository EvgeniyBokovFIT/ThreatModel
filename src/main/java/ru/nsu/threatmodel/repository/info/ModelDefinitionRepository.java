package ru.nsu.threatmodel.repository.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.threatmodel.entity.info.ModelDefinition;

import java.util.List;

@Repository
public interface ModelDefinitionRepository extends JpaRepository<ModelDefinition, Long> {
    List<ModelDefinition> getByThreatModelId(Long modelId);
}