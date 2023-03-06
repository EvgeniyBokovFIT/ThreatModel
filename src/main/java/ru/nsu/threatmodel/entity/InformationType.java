package ru.nsu.threatmodel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "information_types")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class InformationType {
    @Id
    private Long id;

    private String type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "information_type_operation",
            joinColumns = @JoinColumn(name = "information_type_id"),
            inverseJoinColumns = @JoinColumn(name = "operation_id")
    )
    private Set<OperationOnData> operations;
}
