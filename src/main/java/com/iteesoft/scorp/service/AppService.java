package com.iteesoft.scorp.service;

import com.iteesoft.scorp.dto.PostDto;
import com.iteesoft.scorp.dto.UserDto;
import com.iteesoft.scorp.model.Post;
import com.iteesoft.scorp.dto.PostWrapper;
import com.iteesoft.scorp.model.User;

import java.util.List;

public interface AppService {
    User create(UserDto userInfo);
    Post create(PostDto postInfo, int id);

    List<Post> viewUserPosts(int id);

    void follow(int followerId, int userId);
    void unFollow(int followerId, int userId);

    List<User> viewFollowers(int id);
    List<User> viewFollowing(int id);

    List<Post> viewTimeline(int id);
    List<User> getUsers();

    void likePost(int userId, int postId);
    void unlikePost(int userId, int postId);

    List<Post> getPosts(PostWrapper wrapper);
}
