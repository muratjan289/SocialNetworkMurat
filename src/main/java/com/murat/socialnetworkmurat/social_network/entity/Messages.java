package com.murat.socialnetworkmurat.social_network.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "messages")
public class Messages {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @Column(name ="time")
    private Date date;

    @Column(name ="content")
    private String content;

    @Column(name = "from_id")
    private int from;

    @Column(name = "to_id")
    private int to;

//    @Column(name ="from_id")
//    private  int from_id;
//
//    @Column(name ="to_id")
//    private int to_id;
//
@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE
        , CascadeType.REFRESH, CascadeType.DETACH})
@JoinTable(
        name = "messages"
        ,joinColumns = @JoinColumn(name = "to_id")
        ,inverseJoinColumns = @JoinColumn(name = "from_id")
)
private List<User> users;


    public Messages( String content, int from, int to) {
        this.content = content;
        this.from = from;
        this.to = to;
    }

    public Messages(Date date, String content) {
        this.date = date;
        this.content = content;


    }

    public Messages() {

    }

    @JsonBackReference
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
