package com.iteesoft.scorp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Likes extends Base {

    @ManyToOne(targetEntity = Post.class)
    private Post post;
    @ManyToOne(targetEntity = User.class)
    private User user;
}
