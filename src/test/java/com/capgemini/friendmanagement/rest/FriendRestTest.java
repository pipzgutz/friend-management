package com.capgemini.friendmanagement.rest;

import com.capgemini.friendmanagement.entity.Friend;
import com.capgemini.friendmanagement.request.ListOfFriendsRequest;
import com.capgemini.friendmanagement.response.FriendResponse;
import com.capgemini.friendmanagement.service.FriendConnectionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FriendRest.class)
public class FriendRestTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private FriendConnectionService friendConnectionService;

    @Test
    @Ignore
    public void addConnection() throws Exception {
        ListOfFriendsRequest friendRequest = new ListOfFriendsRequest(
                Arrays.asList(new Friend("andy@example.com"), new Friend("john@example.com")));

        when(friendConnectionService.save(friendRequest.getFriends())).thenReturn(new FriendResponse(true));

        mockMvc.perform(put("/friend/add-connection")
        .content(objectMapper.writeValueAsString(friendRequest)))
                .andExpect(status().isOk());
    }
}