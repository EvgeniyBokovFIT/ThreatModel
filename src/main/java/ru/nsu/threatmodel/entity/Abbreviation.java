package ru.nsu.threatmodel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "abbreviations")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Abbreviation {
    @Id
    private Long id;
    private String abbreviation;
    private String decoding;
}
