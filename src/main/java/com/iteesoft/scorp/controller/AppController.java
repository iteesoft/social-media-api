package com.iteesoft.scorp.controller;


import com.iteesoft.scorp.dto.PostDto;
import com.iteesoft.scorp.dto.UserDto;
import com.iteesoft.scorp.model.Post;
import com.iteesoft.scorp.dto.PostWrapper;
import com.iteesoft.scorp.model.User;
import com.iteesoft.scorp.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class AppController {

    private final AppService userService;

    @PostMapping("/new")  //ok
    public ResponseEntity<User> createAccount(@RequestBody UserDto userInfo) {
        return new ResponseEntity<>(userService.create(userInfo), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/posts/new")  //ok
    public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody PostDto message) {
        return new ResponseEntity<>(userService.create(message, id), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/{userId}") //ok
    public ResponseEntity<?> follow(@PathVariable int id, @PathVariable("userId") int userId) {
        userService.follow(id, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/{userId}")
    public ResponseEntity<?> unfollow(@PathVariable int id, @PathVariable("userId") int userId) {
        userService.unFollow(id, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/posts") //ok
    public ResponseEntity<List<Post>> viewPosts(@PathVariable int id) {
        return new ResponseEntity<>(userService.viewUserPosts(id), OK);
    }

    @PostMapping("/{userId}/posts/{id}")
    public ResponseEntity<?> likePost(@PathVariable int userId, @PathVariable int id) {
        userService.likePost(userId, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/posts/{id}")
    public ResponseEntity<?> unlikePost(@PathVariable int userId, @PathVariable int id) {
        userService.likePost(userId, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<List<User>> viewFollowers(@PathVariable int id) {
        return new ResponseEntity<> (userService.viewFollowers(id), OK);
    }

    @GetMapping("/{id}/following")
    public ResponseEntity<List<User>> viewFollowing(@PathVariable int id) {
        return new ResponseEntity<> (userService.viewFollowing(id), OK);
    }

    @GetMapping("/{id}/timeline")
    public ResponseEntity<List<Post>> viewTimeline(@PathVariable int id) {
        return new ResponseEntity<>(userService.viewTimeline(id), OK);
    }

    @GetMapping() //ok
    public ResponseEntity<?> viewAllUsers() {
        return new ResponseEntity<>(userService.getUsers(), OK);
    }

    @PostMapping(value = "/posts")
    public ResponseEntity<List<Post>> getPosts(@RequestBody PostWrapper postIds) {
        return new ResponseEntity<>((userService.getPosts(postIds)), OK);
    }
}