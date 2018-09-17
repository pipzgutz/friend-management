package com.capgemini.friendmanagement.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private Friend friend;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend_connected_to_id")
    @NotNull
    private Friend friendConnectedTo;

    @Column(name = "subscribed")
    private boolean isSubscribed;

    public FriendConnection() {
    }

    public FriendConnection(Friend friend, Friend friendConnectedTo) {
        this.friend = friend;
        this.friendConnectedTo = friendConnectedTo;
    }

    public FriendConnection(Friend friend, Friend friendConnectedTo, boolean isSubscribed) {
        this.friend = friend;
        this.friendConnectedTo = friendConnectedTo;
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

    public Friend getFriendConnectedTo() {
        return friendConnectedTo;
    }

    public void setFriendConnectedTo(Friend friendConnectedTo) {
        this.friendConnectedTo = friendConnectedTo;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }
}
