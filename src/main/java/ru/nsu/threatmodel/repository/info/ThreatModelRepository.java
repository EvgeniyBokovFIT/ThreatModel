package ru.nsu.threatmodel.repository.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.threatmodel.entity.info.ThreatModel;

@Repository
public interface ThreatModelRepository extends JpaRepository<ThreatModel, Long> {
}
