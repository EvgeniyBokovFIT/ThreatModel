package ru.nsu.threatmodel.repository.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.threatmodel.entity.info.ModelAbbreviation;

import java.util.List;

@Repository
public interface ModelAbbreviationRepository extends JpaRepository<ModelAbbreviation, Long> {
    List<ModelAbbreviation> getByThreatModelId(Long modelId);
}
