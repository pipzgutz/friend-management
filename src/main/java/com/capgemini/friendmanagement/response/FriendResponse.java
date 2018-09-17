package com.capgemini.friendmanagement.response;

import java.util.List;

public class FriendResponse {
    private boolean isSuccess;
    private List<String> recipients;
    private List<String> friends;
    private String count;

    public FriendResponse() {
    }

    public FriendResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public FriendResponse(boolean isSuccess, List<String> recipients, List<String> friends, String count) {
        this.isSuccess = isSuccess;
        this.recipients = recipients;
        this.friends = friends;
        this.count = count;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
