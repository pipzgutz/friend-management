package com.capgemini.friendmanagement.dao;

import com.capgemini.friendmanagement.entity.FriendConnection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendConnectionDao extends CrudRepository<FriendConnection, Long> {
    List<FriendConnection> findAll();

    @Query("SELECT fc FROM FriendConnection fc INNER JOIN fc.friend f WHERE f.email = :email")
    List<FriendConnection> findByFriendEmail(String email);

    @Query("SELECT fc FROM FriendConnection fc INNER JOIN fc.friend f WHERE f.friend1Email = :email AND fc.friendConnectedTo.email = :friend2Email")
    FriendConnection findByFriendAndOtherFriendEmail(String friend1Email, String friend2Email);
}
