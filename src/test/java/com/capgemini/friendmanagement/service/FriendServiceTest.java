package com.capgemini.friendmanagement.service;

import com.capgemini.friendmanagement.dao.FriendDao;
import com.capgemini.friendmanagement.entity.Friend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FriendService.class)
public class FriendServiceTest {
    @Autowired
    private FriendService friendService;

    @MockBean
    private FriendDao friendDao;

    @Test
    public void findByEmail() {
        String email = "andy@example.com";

        Friend friend = new Friend(email);

        when(friendDao.findByEmail(email)).thenReturn(friend);

        Friend searchFriend = friendService.findByEmail(email);

        assertThat(searchFriend).isNotNull();
        assertThat(searchFriend.getEmail()).isEqualTo(email);
    }
}