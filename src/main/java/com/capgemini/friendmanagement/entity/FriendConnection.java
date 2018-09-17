package com.capgemini.friendmanagement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "friend_connection")
public class FriendConnection implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_connection_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend_id")
    private Friend friend;

    @Column(name = "subscribed")
    private boolean isSubscribed;

    public FriendConnection() {
    }

    public FriendConnection(Friend friend) {
        this.friend = friend;
    }

    public FriendConnection(Friend friend, boolean isSubscribed) {
        this.friend = friend;
        this.isSubscribed = isSubscribed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }
}
