package com.capgemini.friendmanagement.request;

import com.capgemini.friendmanagement.entity.Friend;

import java.util.List;

public class GenericListOfFriendsRequest {
    private List<Friend> friends;

    public GenericListOfFriendsRequest() {
    }

    public GenericListOfFriendsRequest(List<Friend> friends) {
        this.friends = friends;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}
