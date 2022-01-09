package com.iteesoft.scorp.repository;

import com.iteesoft.scorp.model.Likes;
import com.iteesoft.scorp.model.Post;
import com.iteesoft.scorp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepo extends JpaRepository<Likes, Integer> {
    Likes findByUserAndPost(User user, Post post);
    List<Likes> findAllByPost_Id(int id);
}
