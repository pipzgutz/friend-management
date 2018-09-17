package com.capgemini.friendmanagement.rest;

import com.capgemini.friendmanagement.entity.Friend;
import com.capgemini.friendmanagement.request.GenericFriendRequest;
import com.capgemini.friendmanagement.response.GenericFriendResponse;
import com.capgemini.friendmanagement.service.FriendConnectionService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/friend")
public class FriendRest {
    private final FriendConnectionService friendConnectionService;

    public FriendRest(FriendConnectionService friendConnectionService) {
        this.friendConnectionService = friendConnectionService;
    }

    @PutMapping(value = "add-connection")
    public GenericFriendResponse addConnection(@RequestBody GenericFriendRequest friendRequest) {
        return friendConnectionService.save(friendRequest);
    }
}
