package ru.nsu.threatmodel.entity.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "info_regulations")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ModelRegulation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "info_regulations_id_seq")
    @SequenceGenerator(name = "info_regulations_id_seq", allocationSize = 1)
    private Long id;
    private String regulation;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private ThreatModel threatModel;
}
