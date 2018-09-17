package com.capgemini.friendmanagement.service;

import com.capgemini.friendmanagement.dao.FriendConnectionDao;
import com.capgemini.friendmanagement.entity.Friend;
import com.capgemini.friendmanagement.entity.FriendConnection;
import com.capgemini.friendmanagement.response.GenericFriendResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendConnectionService {
    private final FriendConnectionDao friendConnectionDao;

    public FriendConnectionService(FriendConnectionDao friendConnectionDao) {
        this.friendConnectionDao = friendConnectionDao;
    }

    public GenericFriendResponse save(List<Friend> friends) {
        FriendConnection friendConnection = new FriendConnection(friends.get(0), friends.get(1));
        friendConnectionDao.save(friendConnection);

        return new GenericFriendResponse(true);
    }
}
