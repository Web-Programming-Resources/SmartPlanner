package com.smartplanner.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    private int id;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 100)
    @Getter(onMethod = @__(@JsonIgnore))
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Plan> plans;
}
