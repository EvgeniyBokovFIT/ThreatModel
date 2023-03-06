package ru.nsu.threatmodel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "operations_on_data")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class OperationOnData {
    @Id
    private Long id;

    private String name;
}
