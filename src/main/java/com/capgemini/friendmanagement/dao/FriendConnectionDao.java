package com.capgemini.friendmanagement.dao;

import com.capgemini.friendmanagement.entity.FriendConnection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendConnectionDao extends CrudRepository<FriendConnection, Long> {
    List<FriendConnection> findAll();

    @Query("SELECT fc FROM FriendConnection fc INNER JOIN fc.friend f WHERE f.email = :email")
    List<FriendConnection> findByFriendEmail(@Param("email") String email);
}
