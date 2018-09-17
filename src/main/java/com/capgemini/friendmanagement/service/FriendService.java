package com.capgemini.friendmanagement.service;

import com.capgemini.friendmanagement.dao.FriendDao;
import com.capgemini.friendmanagement.entity.Friend;
import com.capgemini.friendmanagement.entity.FriendConnection;
import com.capgemini.friendmanagement.request.GenericFriendRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
    private final FriendDao friendDao;

    public FriendService(FriendDao friendDao) {
        this.friendDao = friendDao;
    }

    public void saveAll(List<Friend> friends) {
        friendDao.save(friends);
    }

    public void save(Friend friend) {
        friendDao.save(friend);
    }
}
