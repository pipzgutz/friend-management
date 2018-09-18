package com.capgemini.friendmanagement.util;

import com.capgemini.friendmanagement.entity.Friend;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FriendManagerUtil {
    public List<Friend> emailsToFriends(List<String> emails) {
        return emails.stream()
                .map(Friend::new)
                .collect(Collectors.toList());
    }
}
