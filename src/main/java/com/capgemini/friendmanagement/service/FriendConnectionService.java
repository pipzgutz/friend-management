package com.capgemini.friendmanagement.service;

import com.capgemini.friendmanagement.dao.FriendConnectionDao;
import com.capgemini.friendmanagement.entity.Friend;
import com.capgemini.friendmanagement.entity.FriendConnection;
import com.capgemini.friendmanagement.request.EmailRequest;
import com.capgemini.friendmanagement.request.ListOfFriendsRequest;
import com.capgemini.friendmanagement.response.FriendResponse;
import org.springframework.stereotype.Service;
import org.springframework.validation.ValidationUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendConnectionService {
    private final FriendService friendService;
    private final FriendConnectionDao friendConnectionDao;

    public FriendConnectionService(FriendService friendService, FriendConnectionDao friendConnectionDao) {
        this.friendService = friendService;
        this.friendConnectionDao = friendConnectionDao;
    }

    public FriendResponse save(ListOfFriendsRequest friendRequest) {
        List<Friend> friends = friendRequest.getFriends();

        Friend friend1 = friends.get(0);
        Friend friend2 = friends.get(1);

        FriendConnection friendConnection1 = new FriendConnection(friend1, friend2);
        FriendConnection friendConnection2 = new FriendConnection(friend2, friend1);

        // save the friend connections
        friendConnectionDao.save(Arrays.asList(friendConnection1, friendConnection2));

        return new FriendResponse(true);
    }

    public FriendResponse getFriendsList(EmailRequest emailRequest) {
        List<FriendConnection> friendConnections = friendConnectionDao.findByFriendEmail(emailRequest.getEmail());

        if (friendConnections != null && !friendConnections.isEmpty()) {
            List<String> friendList = friendConnections.stream()
                    .map(friendConnection -> friendConnection.getFriendConnectedTo().getEmail())
                    .collect(Collectors.toList());
            return new FriendResponse(true, null, friendList, friendList.size() + "");
        } else {
            return new FriendResponse(false, null, null, null);
        }
    }
}
