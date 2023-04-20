package ru.nsu.threatmodel.entity.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "info_system_description")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SystemDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "info_system_description_id_seq")
    @SequenceGenerator(name = "info_system_description_id_seq", allocationSize = 1)
    private Long id;

    private String generalInformation;

    private String systemName;

    @OneToOne
    @JoinColumn(name = "model_id")
    private ThreatModel threatModel;
}
