package ru.nsu.threatmodel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.threatmodel.entity.Definition;

import java.util.List;

@Repository
public interface DefinitionRepository extends CrudRepository<Definition, Long> {
    List<Definition> findByDefinitionStartsWithIgnoreCase(String definition);
}
