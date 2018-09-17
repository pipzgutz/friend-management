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

    public FriendResponse save(List<Friend> friends) {
        Friend friend1 = getFriendOrCreateNew(friends.get(0));
        Friend friend2 = getFriendOrCreateNew(friends.get(1));

        FriendConnection friendConnection1 = new FriendConnection(friend1, friend2);
        FriendConnection friendConnection2 = new FriendConnection(friend2, friend1);

        // save the friend connections
        friendConnectionDao.save(Arrays.asList(friendConnection1, friendConnection2));

        return new FriendResponse(true);
    }

    public FriendResponse getFriendsList(String email) {
        List<FriendConnection> friendConnections = friendConnectionDao.findByFriendEmail(email);

        if (friendConnections != null && !friendConnections.isEmpty()) {
            List<String> friendList = friendConnections.stream()
                    .map(friendConnection -> friendConnection.getFriendConnectedTo().getEmail())
                    .collect(Collectors.toList());
            return new FriendResponse(true, null, friendList, friendList.size() + "");
        }

        return emptyFriendResponse();
    }

    public FriendResponse getCommonFriends(List<Friend> friends) {
        // TODO poor mans solution, enhance in a future commit
        Friend friend1 = getFriendOrCreateNew(friends.get(0));
        Friend friend2 = getFriendOrCreateNew(friends.get(1));

        Set<String> emails = new HashSet<>();

        addCommonFriendsToSet(friend1, emails);
        addCommonFriendsToSet(friend2, emails);

        emails.remove(friend1.getEmail());
        emails.remove(friend2.getEmail());

        if (!emails.isEmpty()) {
            return new FriendResponse(true, null, new ArrayList<>(emails), emails.size() + "");
        }

        return emptyFriendResponse();
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

    private FriendResponse emptyFriendResponse() {
        return new FriendResponse(false, null, null, null);
    }

    public FriendResponse subscribeToFriendConnection(String friend1Email, String friend2Email) {
        FriendConnection friendConnection = subscribeUnsubscribe(friend1Email, friend2Email, true);

        if (friendConnection != null) {
            return new FriendResponse(true);
        }

        return emptyFriendResponse();
    }

    public FriendResponse unsubscribeToFriendConnection(String friend1Email, String friend2Email) {
        FriendConnection friendConnection = subscribeUnsubscribe(friend1Email, friend2Email, false);

        if (friendConnection != null) {
            return new FriendResponse(true);
        }

        return emptyFriendResponse();
    }

    private FriendConnection subscribeUnsubscribe(String friend1Email, String friend2Email, boolean isSubscribed) {
        FriendConnection friendConnection = friendConnectionDao.findByFriendAndOtherFriendEmail(friend1Email, friend2Email);

        friendConnection.setSubscribed(isSubscribed);

        return friendConnectionDao.save(friendConnection);
    }

    public FriendResponse blockFriendConnection(String friend1Email, String friend2Email) {
        FriendConnection friendConnection = blockUnblock(friend1Email, friend2Email, true);

        if (friendConnection != null) {
            return new FriendResponse(true);
        }

        return emptyFriendResponse();
    }

    public FriendResponse unblockFriendConnection(String friend1Email, String friend2Email) {
        FriendConnection friendConnection = blockUnblock(friend1Email, friend2Email, false);

        if (friendConnection != null) {
            return new FriendResponse(true);
        }

        return emptyFriendResponse();
    }

    private FriendConnection blockUnblock(String friend1Email, String friend2Email, boolean isBlocked) {
        FriendConnection friendConnection = friendConnectionDao.findByFriendAndOtherFriendEmail(friend1Email, friend2Email);

        friendConnection.setBlocked(isBlocked);

        return friendConnectionDao.save(friendConnection);
    }
}
