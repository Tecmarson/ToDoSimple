package com.api.todosimple.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username",length = 100, nullable = false, unique = true)
    @Size(min = 10, max = 100)
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "password", length = 60, nullable = false)
    @Size(min = 8, max = 60)
    @NotNull
    @NotEmpty
    private String password;
}
