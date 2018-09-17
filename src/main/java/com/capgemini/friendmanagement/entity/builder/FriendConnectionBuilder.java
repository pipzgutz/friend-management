package com.capgemini.friendmanagement.entity.builder;

import com.capgemini.friendmanagement.entity.Friend;
import com.capgemini.friendmanagement.entity.FriendConnection;

public final class FriendConnectionBuilder {
    private Long id;
    private Friend friend;
    private Friend friendConnectedTo;
    private boolean isSubscribed;
    private boolean isBlocked;

    private FriendConnectionBuilder() {
    }

    public static FriendConnectionBuilder aFriendConnection() {
        return new FriendConnectionBuilder();
    }

    public FriendConnectionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public FriendConnectionBuilder withFriend(Friend friend) {
        this.friend = friend;
        return this;
    }

    public FriendConnectionBuilder withFriendConnectedTo(Friend friendConnectedTo) {
        this.friendConnectedTo = friendConnectedTo;
        return this;
    }

    public FriendConnectionBuilder withIsSubscribed(boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
        return this;
    }

    public FriendConnectionBuilder withIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
        return this;
    }

    public FriendConnection build() {
        FriendConnection friendConnection = new FriendConnection(friend, friendConnectedTo, isSubscribed, isBlocked);
        friendConnection.setId(id);
        return friendConnection;
    }
}
