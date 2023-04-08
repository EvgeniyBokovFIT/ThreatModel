package ru.nsu.threatmodel.repository.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.threatmodel.entity.info.DefinitionModified;

import java.util.List;

@Repository
public interface ModifiedDefinitionRepository extends JpaRepository<DefinitionModified, Long> {
    List<DefinitionModified> getByThreatModelId(Long modelId);
}