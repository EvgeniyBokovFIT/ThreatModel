package ru.nsu.threatmodel.repository.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.threatmodel.entity.info.SystemInformation;

import java.util.List;

@Repository
public interface SystemInformationRepository extends JpaRepository<SystemInformation, Long> {
    List<SystemInformation> getByThreatModelId(Long id);
}
