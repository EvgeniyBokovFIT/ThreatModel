package ru.nsu.threatmodel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "objects_of_influence")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ObjectOfInfluence {
    @Id
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "object_id")
    private Set<Component> components;

}
