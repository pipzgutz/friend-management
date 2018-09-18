package com.capgemini.friendmanagement.request;

import java.util.List;

public class ListOfFriendsRequest {
    private List<String> friends;

    public ListOfFriendsRequest() {
    }

    public ListOfFriendsRequest(List<String> friends) {
        this.friends = friends;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
