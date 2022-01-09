package com.iteesoft.scorp.repository;

import com.iteesoft.scorp.model.Post;
import com.iteesoft.scorp.dto.PostWrapper;
import com.iteesoft.scorp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByOwnerOrderByCreatedAtDesc(User user);

    @Query(value = "select * from post where id = postIds", nativeQuery = true)
    List<Post> findAllPostWithIds(PostWrapper postIds);

    List<Post> findAll();
}
