package ru.nsu.threatmodel.repository.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.threatmodel.entity.info.ModelRegulation;

import java.util.List;

@Repository
public interface ModelRegulationRepository extends JpaRepository<ModelRegulation, Long> {
    List<ModelRegulation> getByThreatModelId(Long modelId);
}
