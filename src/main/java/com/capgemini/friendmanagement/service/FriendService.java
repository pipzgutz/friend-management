package com.capgemini.friendmanagement.service;

import com.capgemini.friendmanagement.dao.FriendDao;
import com.capgemini.friendmanagement.entity.Friend;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendService {
    private final FriendDao friendDao;

    public FriendService(FriendDao friendDao) {
        this.friendDao = friendDao;
    }

    public Friend save(Friend friend) {
        return friendDao.save(friend);
    }

    public List<Friend> saveAll(List<Friend> friends) {
        List<Friend> toReturn = new ArrayList<>();

        friendDao.save(friends).forEach(toReturn::add);

        return toReturn;
    }

    public Friend findByEmail(String email) {
        return friendDao.findByEmail(email);
    }
}
