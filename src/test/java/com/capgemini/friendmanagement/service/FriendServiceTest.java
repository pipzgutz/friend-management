package com.capgemini.friendmanagement.service;

import com.capgemini.friendmanagement.dao.FriendDao;
import com.capgemini.friendmanagement.entity.Friend;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FriendService.class)
public class FriendServiceTest {
    @Autowired
    private FriendService friendService;

    @MockBean
    private FriendDao friendDao;

    private String email;

    private Friend friend;

    @Before
    public void setUp() {
        email = "andy@example.com";
        friend = new Friend(email);
    }

    @Test
    public void save() {
        when(friendDao.save(friend)).thenReturn(friend);

        Friend returnedFriend = friendService.save(friend);

        assertThat(returnedFriend).isNotNull();
        assertThat(returnedFriend.getEmail()).isEqualTo(email);
    }

    @Test
    public void saveAll() {
        Friend friend2 = new Friend("john@example.com");
        List<Friend> friends = Arrays.asList(this.friend, friend2);
        when(friendDao.save(friends)).thenReturn(friends);

        List<Friend> returnedFriends = friendService.saveAll(friends);

        assertThat(returnedFriends).isNotNull();
        assertThat(returnedFriends.size()).isEqualTo(2);
        assertThat(returnedFriends).contains(friend, friend2);
    }

    @Test
    public void findByEmail() {
        when(friendDao.findByEmail(email)).thenReturn(friend);

        Friend searchFriend = friendService.findByEmail(email);

        assertThat(searchFriend).isNotNull();
        assertThat(searchFriend.getEmail()).isEqualTo(email);
    }

    @Test
    public void findByEmail_Null() {
        when(friendDao.findByEmail(email)).thenReturn(null);

        Friend searchFriend = friendService.findByEmail(email);

        assertThat(searchFriend).isNull();
    }
}