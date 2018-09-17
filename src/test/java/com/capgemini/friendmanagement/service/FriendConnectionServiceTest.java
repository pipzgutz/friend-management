package com.capgemini.friendmanagement.service;

import com.capgemini.friendmanagement.dao.FriendConnectionDao;
import com.capgemini.friendmanagement.entity.Friend;
import com.capgemini.friendmanagement.entity.FriendConnection;
import com.capgemini.friendmanagement.request.EmailRequest;
import com.capgemini.friendmanagement.request.ListOfFriendsRequest;
import com.capgemini.friendmanagement.response.FriendResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    private Friend friend1;
    private Friend friend2;
    private FriendConnection friendConnection1;
    private FriendConnection friendConnection2;
    private EmailRequest emailRequest;

    @Before
    public void setUp() {
        // create the friends
        friend1 = new Friend("andy@example.com");
        friend2 = new Friend("john@example.com");

        friendConnection1 = new FriendConnection(friend1, friend2);
        friendConnection2 = new FriendConnection(friend2, friend1);

        emailRequest = new EmailRequest(friend1.getEmail());
    }

    @Test
    public void save() {
        ListOfFriendsRequest friendRequest = new ListOfFriendsRequest(Arrays.asList(friend1, friend2));

        doNothing().when(friendService).saveAll(friendRequest.getFriends());

        List<FriendConnection> friendConnections = Arrays.asList(friendConnection1, friendConnection2);
        when(friendConnectionDao.save(friendConnections)).thenReturn(friendConnections);

        FriendResponse response = friendConnectionService.save(friendRequest);

        assertThat(response.isSuccess()).isTrue();
    }

    @Test
    public void findByFriendEmail() {
        List<FriendConnection> friendConnections = Collections.singletonList(new FriendConnection(friend1, friend2));

        when(friendConnectionDao.findByFriendEmail(emailRequest.getEmail())).thenReturn(friendConnections);

        FriendResponse friendResponse = friendConnectionService.getFriendsList(emailRequest);

        List<String> friendConnectedToEmails = friendConnections.stream()
                .map(friendConnection -> friendConnection.getFriendConnectedTo().getEmail())
                .collect(Collectors.toList());

        assertThat(friendResponse).isNotNull();
        assertThat(friendResponse.isSuccess()).isTrue();
        assertThat(friendResponse.getFriends()).isEqualTo(friendConnectedToEmails);
        assertThat(friendResponse.getCount()).isEqualTo("1");
    }

    @Test
    public void findByFriendEmail_Not_Exists() {
        when(friendConnectionDao.findByFriendEmail(emailRequest.getEmail())).thenReturn(null);

        FriendResponse friendResponse = friendConnectionService.getFriendsList(emailRequest);

        assertThat(friendResponse).isNotNull();
        assertThat(friendResponse.isSuccess()).isFalse();
        assertThat(friendResponse.getFriends()).isEqualTo(null);
        assertThat(friendResponse.getCount()).isEqualTo(null);
    }
}