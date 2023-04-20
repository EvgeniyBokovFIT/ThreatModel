package ru.nsu.threatmodel.entity.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "info_general_provisions")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GeneralProvision {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "info_general_provisions_id_seq")
    @SequenceGenerator(name = "info_general_provisions_id_seq", allocationSize = 1)
    private Long id;

    private String purpose;

    @Column(name = "information_owner")
    private String informationOwner;

    @Column(name = "responsible_officials")
    private String responsibleOfficials;

    @Column(name = "model_developer_organisation")
    private String developerOrganisation;

    @OneToOne
    @JoinColumn(name = "model_id")
    private ThreatModel threatModel;
}
