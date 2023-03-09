package ru.nsu.threatmodel.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissions")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Permission {
    @Id
    private Long id;

    private String name;
}
