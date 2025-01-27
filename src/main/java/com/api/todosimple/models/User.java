package com.api.todosimple.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    public interface createUser {}
    public interface updateUser {}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @Size(groups = createUser.class, min = 10, max = 100)
    @NotNull(groups = createUser.class)
    @NotEmpty(groups = createUser.class)
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    @Size(groups = {createUser.class, updateUser.class}, min = 8, max = 60)
    @NotNull(groups = {createUser.class, updateUser.class})
    @NotEmpty(groups = {createUser.class, updateUser.class})
    private String password;


    @OneToMany(mappedBy = "user")
    private List<Task> task = new ArrayList<>();


    public User () {}

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id)
                && Objects.equals(name, user.name)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, task);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(groups = createUser.class, min = 10, max = 100) @NotNull(groups = createUser.class) @NotEmpty(groups = createUser.class) String getName() {
        return name;
    }

    public void setName(@Size(groups = createUser.class, min = 10, max = 100) @NotNull(groups = createUser.class) @NotEmpty(groups = createUser.class) String name) {
        this.name = name;
    }

    public @Size(groups = {createUser.class, updateUser.class}, min = 8, max = 60) @NotNull(groups = {createUser.class, updateUser.class}) @NotEmpty(groups = {createUser.class, updateUser.class}) String getPassword() {
        return password;
    }

    public void setPassword(@Size(groups = {createUser.class, updateUser.class}, min = 8, max = 60) @NotNull(groups = {createUser.class, updateUser.class}) @NotEmpty(groups = {createUser.class, updateUser.class}) String password) {
        this.password = password;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }
}
