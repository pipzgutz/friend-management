package com.capgemini.friendmanagement.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Friend implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;

    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend_connection_id")
    private List<FriendConnection> friendConnections;

    public Friend() {
    }

    public Friend(String email) {
        this.email = email;
    }

    public Friend(String email, List<FriendConnection> friendConnections) {
        this.email = email;
        this.friendConnections = friendConnections;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<FriendConnection> getFriendConnections() {
        return friendConnections;
    }

    public void setFriendConnections(List<FriendConnection> friendConnections) {
        this.friendConnections = friendConnections;
    }
}
