package com.capgemini.friendmanagement.service;

import com.capgemini.friendmanagement.dao.FriendConnectionDao;
import com.capgemini.friendmanagement.entity.Friend;
import com.capgemini.friendmanagement.entity.FriendConnection;
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
    private String friend1Email;

    @Before
    public void setUp() {
        // create the friends
        friend1 = new Friend("andy@example.com");
        friend2 = new Friend("john@example.com");

        friendConnection1 = new FriendConnection(friend1, friend2);
        friendConnection2 = new FriendConnection(friend2, friend1);

        friend1Email = friend1.getEmail();
    }

    @Test
    public void save() {
        List<Friend> friends = Arrays.asList(friend1, friend2);

        when(friendService.saveAll(friends)).thenReturn(friends);

        List<FriendConnection> friendConnections = Arrays.asList(friendConnection1, friendConnection2);
        when(friendConnectionDao.save(friendConnections)).thenReturn(friendConnections);

        FriendResponse response = friendConnectionService.save(friends);

        assertThat(response.isSuccess()).isTrue();
    }

    @Test
    public void getFriendsList() {
        List<FriendConnection> friendConnections = Collections.singletonList(new FriendConnection(friend1, friend2));

        when(friendConnectionDao.findByFriendEmail(friend1Email)).thenReturn(friendConnections);

        FriendResponse friendResponse = friendConnectionService.getFriendsList(friend1Email);

        List<String> friendConnectedToEmails = friendConnections.stream()
                .map(friendConnection -> friendConnection.getFriendConnectedTo().getEmail())
                .collect(Collectors.toList());

        assertThat(friendResponse).isNotNull();
        assertThat(friendResponse.isSuccess()).isTrue();
        assertThat(friendResponse.getFriends()).isEqualTo(friendConnectedToEmails);
        assertThat(friendResponse.getCount()).isEqualTo("1");
    }

    @Test
    public void getFriendsList_Email_Null() {
        when(friendConnectionDao.findByFriendEmail(friend1Email)).thenReturn(null);

        FriendResponse friendResponse = friendConnectionService.getFriendsList(friend1Email);

        assertThat(friendResponse).isNotNull();
        assertThat(friendResponse.isSuccess()).isFalse();
        assertThat(friendResponse.getFriends()).isEqualTo(null);
        assertThat(friendResponse.getCount()).isEqualTo(null);
    }

    @Test
    public void getCommonFriends() {
        Friend friend3 = new Friend("james@example.com");
        Friend friend4 = new Friend("jessy@example.com");

        FriendConnection friendConnection1_3 = new FriendConnection(friend1, friend3);
        FriendConnection friendConnection1_4 = new FriendConnection(friend1, friend4);

        FriendConnection friendConnection2_3 = new FriendConnection(friend2, friend3);
        FriendConnection friendConnection2_4 = new FriendConnection(friend2, friend4);

        List<FriendConnection> friendConnections1 = Arrays.asList(friendConnection1, friendConnection2,
                friendConnection1_3, friendConnection1_4);
        when(friendConnectionDao.findByFriendEmail(friend1Email)).thenReturn(friendConnections1);

        List<FriendConnection> friendConnections2 = Arrays.asList(friendConnection1, friendConnection2,
                friendConnection2_3, friendConnection2_4);
        when(friendConnectionDao.findByFriendEmail(friend1Email)).thenReturn(friendConnections2);

        FriendResponse friendResponse = friendConnectionService.getCommonFriends(Arrays.asList(friend1, friend2));

        assertThat(friendResponse).isNotNull();
        assertThat(friendResponse.isSuccess()).isTrue();
        assertThat(friendResponse.getFriends()).contains(friend3.getEmail(), friend4.getEmail());
        assertThat(friendResponse.getCount()).isEqualTo("2");
    }
}