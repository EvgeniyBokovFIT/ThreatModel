package ru.nsu.threatmodel.entity.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "info_abbreviations")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AbbreviationModified {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "info_abbreviations_id_seq")
    @SequenceGenerator(name = "info_abbreviations_id_seq", allocationSize = 1)
    private Long id;
    private String abbreviation;
    private String decoding;
    @ManyToOne
    private ThreatModel threatModel;
}
