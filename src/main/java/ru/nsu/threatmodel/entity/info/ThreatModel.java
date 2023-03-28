package ru.nsu.threatmodel.entity.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nsu.threatmodel.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "threat_models")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ThreatModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_id_seq")
    @SequenceGenerator(name = "model_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    @ManyToOne
    private User user;
}
