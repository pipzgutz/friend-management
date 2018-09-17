package com.capgemini.friendmanagement.rest;

import com.capgemini.friendmanagement.request.EmailRequest;
import com.capgemini.friendmanagement.request.EmailWithUpdatesRequest;
import com.capgemini.friendmanagement.request.ListOfFriendsRequest;
import com.capgemini.friendmanagement.request.SubscriptionRequest;
import com.capgemini.friendmanagement.response.FriendResponse;
import com.capgemini.friendmanagement.response.builder.FriendResponseBuilder;
import com.capgemini.friendmanagement.service.FriendConnectionService;
import com.capgemini.friendmanagement.service.FriendService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/friend")
public class FriendRest {
    private final FriendService friendService;
    private final FriendConnectionService friendConnectionService;

    public FriendRest(FriendService friendService, FriendConnectionService friendConnectionService) {
        this.friendService = friendService;
        this.friendConnectionService = friendConnectionService;
    }

    @PostMapping("friends-list")
    public ResponseEntity<FriendResponse> getFriendsList(@RequestBody EmailRequest emailRequest) {
        List<String> friendList = friendConnectionService.getFriendsList(emailRequest.getEmail());

        if (CollectionUtils.isNotEmpty(friendList)) {
            return new ResponseEntity<>(FriendResponseBuilder.aFriendResponse()
                    .withIsSuccess(true)
                    .withFriends(friendList)
                    .withCount(friendList.size() + "")
                    .build(), HttpStatus.OK);
        } else {
            return emptyFriendResponse();
        }
    }

    @PostMapping("friends-list-common")
    public ResponseEntity<FriendResponse> getCommonFriendsList(@RequestBody ListOfFriendsRequest friendsRequest) {
        List<String> commonFriends = friendConnectionService.getCommonFriends(friendsRequest.getFriends());

        if (CollectionUtils.isNotEmpty(commonFriends)) {
            return new ResponseEntity<>(FriendResponseBuilder.aFriendResponse()
                    .withIsSuccess(true)
                    .withFriends(commonFriends)
                    .withCount(commonFriends.size() + "")
                    .build(), HttpStatus.OK);
        } else {
            return emptyFriendResponse();
        }
    }

    @PostMapping("add-connection")
    public ResponseEntity<FriendResponse> addConnection(@RequestBody ListOfFriendsRequest friendRequest) {
        friendConnectionService.save(friendRequest.getFriends());
        return okResponse();
    }

    @PostMapping("subscribe")
    public ResponseEntity<FriendResponse> subscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
        friendConnectionService.subscribeToFriendConnection(
                subscriptionRequest.getRequestor(), subscriptionRequest.getTarget());
        return okResponse();
    }

    @PostMapping("unsubscribe")
    public ResponseEntity<FriendResponse> unsubscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
        friendConnectionService.unsubscribeToFriendConnection(
                subscriptionRequest.getRequestor(), subscriptionRequest.getTarget());
        return okResponse();
    }

    @PostMapping("block")
    public ResponseEntity<FriendResponse> block(@RequestBody SubscriptionRequest subscriptionRequest) {
        friendConnectionService.blockFriendConnection(
                subscriptionRequest.getRequestor(), subscriptionRequest.getTarget());
        return okResponse();
    }

    @PostMapping("unblock")
    public ResponseEntity<FriendResponse> unblock(@RequestBody SubscriptionRequest subscriptionRequest) {
        friendConnectionService.unblockFriendConnection(
                subscriptionRequest.getRequestor(), subscriptionRequest.getTarget());
        return okResponse();
    }

    @PostMapping("friend-list-with-updates")
    public ResponseEntity<FriendResponse> findAllEmailsWithUpdatesByEmail(@RequestBody EmailWithUpdatesRequest emailWithUpdatesRequest) {
        List<String> allEmailsWithUpdates = friendConnectionService.findAllEmailsWithUpdatesByEmail(
                emailWithUpdatesRequest.getEmail(), emailWithUpdatesRequest.getText());
        return new ResponseEntity<>(
                FriendResponseBuilder.aFriendResponse()
                        .withIsSuccess(true)
                        .withFriends(allEmailsWithUpdates)
                        .build(),
                HttpStatus.OK);
    }

    private ResponseEntity<FriendResponse> okResponse() {
        return new ResponseEntity<>(new FriendResponse(true), HttpStatus.OK);
    }

    private ResponseEntity<FriendResponse> emptyFriendResponse() {
        return new ResponseEntity<>(
                new FriendResponse(false, null, null, null),
                HttpStatus.OK);
    }
}
