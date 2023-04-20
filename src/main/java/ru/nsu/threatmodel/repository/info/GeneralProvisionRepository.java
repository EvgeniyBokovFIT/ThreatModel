package ru.nsu.threatmodel.repository.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.threatmodel.entity.info.GeneralProvision;

import java.util.Optional;

@Repository
public interface GeneralProvisionRepository extends JpaRepository<GeneralProvision, Long> {
    Optional<GeneralProvision> getByThreatModelId(Long modelId);
}
