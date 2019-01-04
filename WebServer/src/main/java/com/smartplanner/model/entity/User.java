package com.smartplanner.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Getter
    @Setter
    @Column(name = "user_id")
    private int id;

    @Getter
    @Setter
    @Column(name = "username", length = 100)
    private String username;

    @Getter
    @Setter
    @Column(name = "email", length = 100)
    private String email;

    @Getter
    @Setter
    @Column(name = "password", length = 100)
    @JsonIgnore
    private String password;
}
