package com.murat.socialnetworkmurat.social_network.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "pwd")
    private String login;

    @Column(name = "status")
    private String status;

    @Column(name = "enabled")
    private int enabled;

    @Column(name = "image")
    private String image;



    @ManyToMany(cascade ={CascadeType.PERSIST,CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_has_users"
            ,joinColumns = @JoinColumn(name = "user_id")
            ,inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> friends = new HashSet<>();



    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE},fetch = FetchType.EAGER)

    @JsonIgnore
    @JoinTable(
            name = "messages"
            ,joinColumns = @JoinColumn(name = "from_id")
             ,inverseJoinColumns = @JoinColumn(name = "to_id")
    )
    private List<Messages> messages;


    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;

    }


    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "role_id")
    private Roles roles;


    public User() {
    }


    public User(int id, String name, String login, String status, int enabled, String image, Roles roles) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.status = status;
        this.enabled = enabled;
        this.image = image;
        this.roles = roles;
    }
    public User( String name, String login, String status, int enabled, String image, Roles roles) {
        this.name = name;
        this.login = login;
        this.status = status;
        this.enabled = enabled;
        this.image = image;
        this.roles = roles;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", status='" + status + '\'' +
                ", enabled=" + enabled +
                ", image='" + image + '\'' +
                '}';
    }

    public Set<User> getFriends() {
        return friends;
    }



    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public User(Set<User> friends) {
        this.friends = friends;
    }

    public User(int id, String name, String login, String status, int enabled, String image) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.status = status;
        this.enabled = enabled;
        this.image = image;
    }

}
