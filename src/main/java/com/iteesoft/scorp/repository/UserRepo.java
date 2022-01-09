package com.iteesoft.scorp.repository;

import com.iteesoft.scorp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "select * from follow_table where id = follower_id", nativeQuery = true)
    List<User> findAllFollowByUserId(int userId);
}
