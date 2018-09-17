package com.capgemini.friendmanagement.dao;

import com.capgemini.friendmanagement.entity.FriendConnection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendConnectionDao extends CrudRepository<FriendConnection, Long> {
    List<FriendConnection> findAll();
}
