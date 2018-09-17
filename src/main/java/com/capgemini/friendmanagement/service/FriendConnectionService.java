package com.capgemini.friendmanagement.service;

import com.capgemini.friendmanagement.dao.FriendConnectionDao;
import com.capgemini.friendmanagement.entity.Friend;
import com.capgemini.friendmanagement.entity.FriendConnection;
import com.capgemini.friendmanagement.response.FriendResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class FriendConnectionService {
    private final FriendService friendService;
    private final FriendConnectionDao friendConnectionDao;

    public FriendConnectionService(FriendService friendService, FriendConnectionDao friendConnectionDao) {
        this.friendService = friendService;
        this.friendConnectionDao = friendConnectionDao;
    }

    public List<FriendConnection> save(List<Friend> friends) {
        Friend friend1 = getFriendOrCreateNew(friends.get(0));
        Friend friend2 = getFriendOrCreateNew(friends.get(1));

        FriendConnection friendConnection1 = new FriendConnection(friend1, friend2);
        FriendConnection friendConnection2 = new FriendConnection(friend2, friend1);

        // save the friend connections
        List<FriendConnection> friendConnections = new ArrayList<>();
        friendConnectionDao.save(Arrays.asList(friendConnection1, friendConnection2)).forEach(friendConnections::add);
        return friendConnections;
    }

    public List<String> getFriendsList(String email) {
        List<FriendConnection> friendConnections = friendConnectionDao.findByFriendEmail(email);

        if (friendConnections != null && !friendConnections.isEmpty()) {
            return friendConnections.stream()
                    .map(friendConnection -> friendConnection.getFriendConnectedTo().getEmail())
                    .collect(Collectors.toList());
        }

        return null;
    }

    public List<String> getCommonFriends(List<Friend> friends) {
        // TODO poor mans solution, enhance in a future commit
        Friend friend1 = getFriendOrCreateNew(friends.get(0));
        Friend friend2 = getFriendOrCreateNew(friends.get(1));

        Set<String> emails = new HashSet<>();

        addCommonFriendsToSet(friend1, emails);
        addCommonFriendsToSet(friend2, emails);

        emails.remove(friend1.getEmail());
        emails.remove(friend2.getEmail());

        return new ArrayList<>(emails);
    }

    private void addCommonFriendsToSet(Friend friend, Set<String> emails) {
        List<FriendConnection> friendConnections1 = friendConnectionDao.findByFriendEmail(friend.getEmail());
        emails.addAll(friendConnections1.stream()
                .map(friendConnection -> friendConnection.getFriendConnectedTo().getEmail())
                .collect(Collectors.toSet()));
    }

    private Friend getFriendOrCreateNew(Friend friend) {
        Friend searchedFriend = friendService.findByEmail(friend.getEmail());

        if (searchedFriend == null) {
            friendService.save(friend);
            return friend;
        }

        return searchedFriend;
    }

    public FriendConnection subscribeToFriendConnection(String friend1Email, String friend2Email) {
        return subscribeUnsubscribe(friend1Email, friend2Email, true);
    }

    public FriendConnection unsubscribeToFriendConnection(String friend1Email, String friend2Email) {
        return subscribeUnsubscribe(friend1Email, friend2Email, false);
    }

    private FriendConnection subscribeUnsubscribe(String friend1Email, String friend2Email, boolean isSubscribed) {
        FriendConnection friendConnection = friendConnectionDao.findByFriendAndOtherFriendEmail(friend1Email, friend2Email);

        friendConnection.setSubscribed(isSubscribed);

        return friendConnectionDao.save(friendConnection);
    }

    public FriendConnection blockFriendConnection(String friend1Email, String friend2Email) {
        return blockUnblock(friend1Email, friend2Email, true);
    }

    public FriendConnection unblockFriendConnection(String friend1Email, String friend2Email) {
        return blockUnblock(friend1Email, friend2Email, false);
    }

    private FriendConnection blockUnblock(String friend1Email, String friend2Email, boolean isBlocked) {
        FriendConnection friendConnection = friendConnectionDao.findByFriendAndOtherFriendEmail(friend1Email, friend2Email);

        friendConnection.setBlocked(isBlocked);

        return friendConnectionDao.save(friendConnection);
    }
}
