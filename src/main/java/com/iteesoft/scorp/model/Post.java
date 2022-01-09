package com.iteesoft.scorp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post extends Base{

    private String description;
    @ManyToOne
    private User owner;
    private String image;
    private Boolean liked;
    private Instant createdAt = Instant.now();
}
