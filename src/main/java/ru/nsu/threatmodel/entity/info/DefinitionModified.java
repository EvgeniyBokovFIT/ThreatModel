package ru.nsu.threatmodel.entity.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "info_definitions")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DefinitionModified {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "info_definitions_id_seq")
    @SequenceGenerator(name = "info_definitions_id_seq", allocationSize = 1)
    private Long id;
    private String definition;
    private String meaning;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private ThreatModel threatModel;
}
