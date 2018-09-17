package com.capgemini.friendmanagement.rest;

import com.capgemini.friendmanagement.request.GenericEmailRequest;
import com.capgemini.friendmanagement.request.GenericListOfFriendsRequest;
import com.capgemini.friendmanagement.response.GenericFriendResponse;
import com.capgemini.friendmanagement.service.FriendConnectionService;
import com.capgemini.friendmanagement.service.FriendService;
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
    public GenericFriendResponse getFriendsList(@RequestBody GenericEmailRequest emailRequest) {
        return friendConnectionService.getFriendsList(emailRequest);
    }

    @PutMapping("add-connection")
    public GenericFriendResponse addConnection(@RequestBody GenericListOfFriendsRequest friendRequest) {
        return friendConnectionService.save(friendRequest);
    }
}
