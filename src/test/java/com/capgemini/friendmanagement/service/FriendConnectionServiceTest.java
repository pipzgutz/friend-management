package com.capgemini.friendmanagement.service;

import com.capgemini.friendmanagement.dao.FriendConnectionDao;
import com.capgemini.friendmanagement.entity.Friend;
import com.capgemini.friendmanagement.entity.FriendConnection;
import com.capgemini.friendmanagement.request.GenericFriendRequest;
import com.capgemini.friendmanagement.response.GenericFriendResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FriendConnectionService.class)
public class FriendConnectionServiceTest {
    @Autowired
    private FriendConnectionService friendConnectionService;

    @MockBean
    private FriendService friendService;

    @MockBean
    private FriendConnectionDao friendConnectionDao;

    @Test
    public void save() {
        Friend friend1 = new Friend("andy@example.com");
        Friend friend2 = new Friend("john@example.com");
        FriendConnection friendConnection = new FriendConnection(friend1, friend2);
        GenericFriendRequest friendRequest = new GenericFriendRequest(Arrays.asList(friend1, friend2));

        doNothing().when(friendService).saveAll(friendRequest.getFriends());

        when(friendConnectionDao.save(friendConnection)).thenReturn(friendConnection);

        GenericFriendResponse response = friendConnectionService.save(friendRequest);

        assertThat(response.isSuccess()).isTrue();
    }
}