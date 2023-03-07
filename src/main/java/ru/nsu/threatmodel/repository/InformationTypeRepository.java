package ru.nsu.threatmodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.threatmodel.entity.InformationType;

import java.util.Optional;

@Repository
public interface InformationTypeRepository extends JpaRepository<InformationType, Long> {
    Optional<InformationType> findByType(String type);
}
