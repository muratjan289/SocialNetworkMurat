package com.murat.socialnetworkmurat.social_network.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "messages")
public class Messages {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @Column(name ="time")
    private LocalDate date;

    @Column(name ="content")
    private String content;

    @Column(name = "from_id")
    private int sender;

    @Column(name = "to_id")
    private int recipient
;

//    @Column(name ="from_id")
//    private  int from_id;
//
//    @Column(name ="to_id")
//    private int to_id;
//
@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE
        , CascadeType.REFRESH, CascadeType.DETACH},fetch = FetchType.EAGER)

@JoinTable(
        name = "messages"
        ,joinColumns = @JoinColumn(name = "to_id")
        ,inverseJoinColumns = @JoinColumn(name = "from_id")
)
private List<User> users;


    public Messages( String content, int sender, int recipient) {
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
    }

    public Messages(LocalDate date, String content, int sender, int recipient) {
        this.date = date;
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public int getRecipient() {
        return recipient
;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public void setRecipient(int recipient
) {
        this.recipient = recipient
;
    }
}
