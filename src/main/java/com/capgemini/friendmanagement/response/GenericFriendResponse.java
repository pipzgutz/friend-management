package com.capgemini.friendmanagement.response;

import com.capgemini.friendmanagement.entity.Friend;

import java.util.List;

public class GenericFriendResponse {
    private boolean isSuccess;
    private List<Friend> friends;
    private int count;

    public GenericFriendResponse() {
    }

    public GenericFriendResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public GenericFriendResponse(boolean isSuccess, List<Friend> friends, int count) {
        this.isSuccess = isSuccess;
        this.friends = friends;
        this.count = count;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
