package com.capgemini.friendmanagement.response.builder;

import com.capgemini.friendmanagement.response.FriendResponse;

import java.util.List;

public final class FriendResponseBuilder {
    private List<String> recipients;
    private List<String> friends;
    private String count;
    private boolean isSuccess;

    private FriendResponseBuilder() {
    }

    public static FriendResponseBuilder aFriendResponse() {
        return new FriendResponseBuilder();
    }

    public FriendResponseBuilder withRecipients(List<String> recipients) {
        this.recipients = recipients;
        return this;
    }

    public FriendResponseBuilder withFriends(List<String> friends) {
        this.friends = friends;
        return this;
    }

    public FriendResponseBuilder withCount(String count) {
        this.count = count;
        return this;
    }

    public FriendResponseBuilder withIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
        return this;
    }

    public FriendResponse build() {
        FriendResponse friendResponse = new FriendResponse(isSuccess);
        friendResponse.setRecipients(recipients);
        friendResponse.setFriends(friends);
        friendResponse.setCount(count);
        return friendResponse;
    }
}
