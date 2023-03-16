package ru.nsu.threatmodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.threatmodel.entity.ObjectOfInfluence;

import java.util.Set;

@Repository
public interface ObjectOfInfluenceRepository extends JpaRepository<ObjectOfInfluence, Long> {
    Set<ObjectOfInfluence> findByNameStartsWithIgnoreCase(String name);
}
