package com.iteesoft.scorp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User extends Base{

    private String fullName;
    private String username;
    private String email;
    private String password;
    private String profilePictures;
    private String bio;
    private Boolean followed;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "follow_table", joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id"))
    private List<User> follow;
}