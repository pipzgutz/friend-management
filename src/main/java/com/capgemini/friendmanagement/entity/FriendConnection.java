package com.capgemini.friendmanagement.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "friend_connection")
public class FriendConnection implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Friend friend1;
    private Friend friend2;

    @Column(name = "subscribed")
    private boolean isSubscribed;

    public FriendConnection() {
    }

    public FriendConnection(Friend friend1, Friend friend2) {
        this.friend1 = friend1;
        this.friend2 = friend2;
    }

    public FriendConnection(Friend friend1, Friend friend2, boolean isSubscribed) {
        this.friend1 = friend1;
        this.friend2 = friend2;
        this.isSubscribed = isSubscribed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Friend getFriend1() {
        return friend1;
    }

    public void setFriend1(Friend friend1) {
        this.friend1 = friend1;
    }

    public Friend getFriend2() {
        return friend2;
    }

    public void setFriend2(Friend friend2) {
        this.friend2 = friend2;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }
}
