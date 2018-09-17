package com.capgemini.friendmanagement.entity.builder;

import com.capgemini.friendmanagement.entity.Friend;

public final class FriendBuilder {
    private Long id;
    private String email;

    private FriendBuilder() {
    }

    public static FriendBuilder aFriend() {
        return new FriendBuilder();
    }

    public FriendBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public FriendBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public Friend build() {
        Friend friend = new Friend();
        friend.setId(id);
        friend.setEmail(email);
        return friend;
    }
}
