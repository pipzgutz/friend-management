package com.capgemini.friendmanagement.service;

import com.capgemini.friendmanagement.dao.FriendDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FriendService.class)
public class FriendServiceTest {
    @Autowired
    private FriendService friendService;

    @MockBean
    private FriendDao friendDao;

    @Test
    public void find() {

    }
}