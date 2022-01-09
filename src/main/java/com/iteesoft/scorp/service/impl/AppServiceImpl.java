package com.iteesoft.scorp.service.impl;

import com.iteesoft.scorp.dto.PostDto;
import com.iteesoft.scorp.dto.UserDto;
import com.iteesoft.scorp.model.Likes;
import com.iteesoft.scorp.model.Post;
import com.iteesoft.scorp.dto.PostWrapper;
import com.iteesoft.scorp.model.User;
import com.iteesoft.scorp.repository.LikeRepo;
import com.iteesoft.scorp.repository.PostRepo;
import com.iteesoft.scorp.repository.UserRepo;
import com.iteesoft.scorp.service.AppService;
import com.iteesoft.scorp.service.exceptions.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {

    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final LikeRepo likeRepo;

    @Override
    public User create(UserDto userInfo) {
        log.info("Creating user "+userInfo.username());
        User user = new User();
        user.setEmail(userInfo.email());
        user.setUsername(userInfo.username());
        user.setPassword(userInfo.password());
        userRepo.save(user);
        return user;
    }

    @Override
    public Post create(PostDto postInfo, int id) {
        User user = userRepo.findById(id).orElseThrow(()-> new AppException("User does not exist"));
        log.info("Creating New post by " + user.getFullName());
        Post newPost = new Post();
        newPost.setDescription(postInfo.description());
        newPost.setImage(postInfo.image());
        newPost.setOwner(user);
        newPost.setLiked(false);
        postRepo.save(newPost);
        return newPost;
    }

    @Override
    public List<Post> viewUserPosts(int id) {
        User user = userRepo.findById(id).orElseThrow(()-> new AppException("User does not exist"));
        log.info("showing list of posts by " + user.getFullName());
        return postRepo.findByOwnerOrderByCreatedAtDesc(user);
    }

    @Transactional
    @Override
    public void follow(int followerId, int userId) {
        User user1 = userRepo.findById(followerId).orElseThrow(()-> new AppException("User does not exist"));
        User user2 = userRepo.findById(userId).orElseThrow(()-> new AppException("User does not exist"));

        user1.getFollow().add(user2);
        user2.setFollowed(true);
        log.info(user1.getFullName() + " now following " + user2.getFullName());
    }

    @Transactional
    @Override
    public void unFollow(int followerId, int userId) {
        User user1 = userRepo.findById(followerId).orElseThrow(()-> new AppException("User does not exist"));
        User user2 = userRepo.findById(userId).orElseThrow(()-> new AppException("User does not exist"));

        user2.getFollow().remove(user1);
        user1.getFollow().remove(user2);
        log.info(user1.getFullName() + " unfollowed " + user2.getFullName());
    }

    @Override
    public List<User> viewFollowers(int id) {
        User user = userRepo.findById(id).orElseThrow(()-> new AppException("User does not exist"));
        log.info("showing list of users following " + user.getFullName());
        return user.getFollow();
    }

    @Override
    public List<User> viewFollowing(int id) {
        User user = userRepo.findById(id).orElseThrow(()-> new AppException("User does not exist"));
        log.info("showing list of users followed by " + user.getFullName());
        return user.getFollow();
    }

    @Override
    public List<Post> viewTimeline(int userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new AppException("User does not exist"));
        log.info("showing timeline of " + user.getFullName());

        List<Post> userPosts = viewUserPosts(userId);
        List<Post> followingPosts = new ArrayList<>();
        List<Post> timeline = new ArrayList<>();

        for (User f : user.getFollow()) {
            List<Post> posts = postRepo.findByOwnerOrderByCreatedAtDesc(f);
            followingPosts.addAll(posts);
        }
        timeline.addAll(userPosts);
        timeline.addAll(followingPosts);
        return timeline;
    }

    @Override
    public List<User> getUsers() {
        log.info("showing list of all users");
        return userRepo.findAll();
    }

    @Override
    @Transactional
    public void likePost(int userId, int postId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new AppException("User does not exist"));
        Post post = postRepo.findById(postId).orElseThrow(()-> new AppException("Post does not exist"));

        Likes likes = new Likes(post, user);
        post.setLiked(true);
        likeRepo.save(likes);
        log.info("Post liked by "+ user.getFullName());
    }

    @Transactional
    @Override
    public void unlikePost(int userId, int postId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new AppException("User does not exist"));
        Post post = postRepo.findById(postId).orElseThrow(()-> new AppException("Post does not exist"));
        Likes likes = likeRepo.findByUserAndPost(user, post);
        likeRepo.delete(likes);
        if (likeRepo.findAllByPost_Id(post.getId()).isEmpty()) {
            post.setLiked(false);
        }
        log.info("Post "+post.getId()+ " unliked by "+ user.getFullName());
    }

    @Override
    public List<Post> getPosts(PostWrapper postIds) {
        log.info("fetching posts with id(s): "+ postIds.getPostIds());

        List<Post> filtered = new ArrayList<>();

        for (int id : postIds.getPostIds()) {
            Optional<Post> post = postRepo.findById(id);
            if (post.isPresent()) {
                filtered.add(post.get());
            } else {
                filtered.add(null);
            }
        }
        return filtered;
    }
}
