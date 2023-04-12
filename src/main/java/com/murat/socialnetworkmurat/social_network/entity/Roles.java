package com.murat.socialnetworkmurat.social_network.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


    @OneToMany(
             mappedBy = "roles"
            , fetch = FetchType.EAGER)
    private List<User> users;

    public Roles(int id) {
        this.id = id;
    }

    public Roles(String name) {
        this.name = name;
    }

    public Roles(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Roles() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }





}
