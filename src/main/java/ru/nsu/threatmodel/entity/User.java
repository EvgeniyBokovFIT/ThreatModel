package ru.nsu.threatmodel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    private Long id;
    private String login;
    private String salt;
    private String password;
    private LocalDateTime createdAt;
    private String refreshToken;
}
