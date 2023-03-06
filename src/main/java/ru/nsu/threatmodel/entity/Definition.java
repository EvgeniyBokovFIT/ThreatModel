package ru.nsu.threatmodel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "definitions")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Definition {

    @Id
    private Long id;

    private String definition;

    private String meaning;
}
