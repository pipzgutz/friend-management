package com.capgemini.friendmanagement.request;

import com.capgemini.friendmanagement.entity.Friend;

import java.util.List;

public class GenericFriendRequest {
    private List<Friend> friends;

    public GenericFriendRequest() {
    }

    public GenericFriendRequest(List<Friend> friends) {
        this.friends = friends;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}
