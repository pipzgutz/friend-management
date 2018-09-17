package com.capgemini.friendmanagement.rest;

import com.capgemini.friendmanagement.request.EmailRequest;
import com.capgemini.friendmanagement.request.ListOfFriendsRequest;
import com.capgemini.friendmanagement.request.SubscriptionRequest;
import com.capgemini.friendmanagement.response.FriendResponse;
import com.capgemini.friendmanagement.service.FriendConnectionService;
import com.capgemini.friendmanagement.service.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(friendConnectionService.getFriendsList(emailRequest.getEmail()), HttpStatus.OK);
    }

    @PostMapping("friends-list-common")
    public ResponseEntity<FriendResponse> getCommonFriendsList(@RequestBody ListOfFriendsRequest friendsRequest) {
        return new ResponseEntity<>(friendConnectionService.getCommonFriends(friendsRequest.getFriends()), HttpStatus.OK);
    }

    @PutMapping("add-connection")
    public ResponseEntity<FriendResponse> addConnection(@RequestBody ListOfFriendsRequest friendRequest) {
        return new ResponseEntity<>(friendConnectionService.save(friendRequest.getFriends()), HttpStatus.OK);
    }

    @PostMapping("subscribe")
    public ResponseEntity<FriendResponse> subscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
        return new ResponseEntity<>(friendConnectionService.subscribeToFriendConnection(
                subscriptionRequest.getRequestor(), subscriptionRequest.getTarget()), HttpStatus.OK);
    }

    @PostMapping("unsubscribe")
    public ResponseEntity<FriendResponse> unsubscribe(@RequestBody SubscriptionRequest subscriptionRequest) {
        return new ResponseEntity<>(friendConnectionService.unsubscribeToFriendConnection(
                subscriptionRequest.getRequestor(), subscriptionRequest.getTarget()), HttpStatus.OK);
    }
}
