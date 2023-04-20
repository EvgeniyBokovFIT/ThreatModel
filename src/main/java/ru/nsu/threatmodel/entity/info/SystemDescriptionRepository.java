package ru.nsu.threatmodel.entity.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemDescriptionRepository extends JpaRepository<SystemDescription, Long> {
    Optional<SystemDescription> getByThreatModelId(Long id);
}
