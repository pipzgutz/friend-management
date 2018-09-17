package com.capgemini.friendmanagement.request;

import com.capgemini.friendmanagement.entity.Friend;

import java.util.List;

public class ListOfFriendsRequest {
    private List<Friend> friends;

    public ListOfFriendsRequest() {
    }

    public ListOfFriendsRequest(List<Friend> friends) {
        this.friends = friends;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}
