package com.capgemini.friendmanagement.dao;

import com.capgemini.friendmanagement.entity.Friend;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendDao extends CrudRepository<Friend, Long> {
    List<Friend> findAll();

    Friend findByEmail(String email);
}
