package ru.nsu.threatmodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.threatmodel.entity.Abbreviation;

import java.util.List;

@Repository
public interface AbbreviationRepository extends JpaRepository<Abbreviation, Long> {
    List<Abbreviation> findByAbbreviationStartsWithIgnoreCase(String abbreviation);
}
