package ru.nsu.threatmodel.entity.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "info_system_information")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SystemInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "info_system_information_id_seq")
    @SequenceGenerator(name = "info_system_information_id_seq", allocationSize = 1)
    private Long id;

    private String informationType;

    private String operations;

    private String composition;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private ThreatModel threatModel;
}
