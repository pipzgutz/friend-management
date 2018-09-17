package com.capgemini.friendmanagement.service;

import com.capgemini.friendmanagement.dao.FriendConnectionDao;
import com.capgemini.friendmanagement.entity.Friend;
import com.capgemini.friendmanagement.entity.FriendConnection;
import com.capgemini.friendmanagement.request.GenericEmailRequest;
import com.capgemini.friendmanagement.request.GenericListOfFriendsRequest;
import com.capgemini.friendmanagement.response.GenericFriendResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FriendConnectionService {
    private final FriendService friendService;
    private final FriendConnectionDao friendConnectionDao;

    public FriendConnectionService(FriendService friendService, FriendConnectionDao friendConnectionDao) {
        this.friendService = friendService;
        this.friendConnectionDao = friendConnectionDao;
    }

    public GenericFriendResponse save(GenericListOfFriendsRequest friendRequest) {
        List<Friend> friends = friendRequest.getFriends();

        Friend friend1 = friends.get(0);
        Friend friend2 = friends.get(1);

        FriendConnection friendConnection1 = new FriendConnection(friend1);
        FriendConnection friendConnection2 = new FriendConnection(friend2);

        // save the friend connections
        friendConnectionDao.save(Arrays.asList(friendConnection1, friendConnection2));

        return new GenericFriendResponse(true);
    }

    public GenericFriendResponse getFriendsList(GenericEmailRequest emailRequest) {
        return null;
    }
}
