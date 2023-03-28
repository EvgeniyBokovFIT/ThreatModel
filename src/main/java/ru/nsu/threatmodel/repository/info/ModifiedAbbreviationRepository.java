package ru.nsu.threatmodel.repository.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.threatmodel.entity.info.AbbreviationModified;

import java.util.List;

@Repository
public interface ModifiedAbbreviationRepository extends JpaRepository<AbbreviationModified, Long> {
    List<AbbreviationModified> getByThreatModelId(Long modelId);
}
