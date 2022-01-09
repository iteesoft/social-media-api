package Algorithm;

import lombok.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergePosts {
    public static void main(String[] args) {
        Post p1 = new Post(2, "blue bug", "url", 5);
        Post p2 = new Post(1,"yellow bug", "url", 4);
        Post p3 = new Post(6, "green bug", "url", 5);
        Post p4 = new Post(3, "orange bug", "url", 5);
        Post p6 = new Post(3, "orange bug", "url", 4);
        Post p5 = new Post(6, "green bug", "url", 5);

        List<List<Post>> posts = List.of(List.of(p1, p2, p3, p4, p5), List.of(p1, p2, p6, p4, p5));
        System.out.println("original posts => \n"+ posts);
        System.out.println("After merged, sort and duplicate removal =>\n"+merge(posts));
    }


    static List<Post> merge(List<List<Post>> posts) {
        List<Post> merged = new ArrayList<>();
        for (List<Post> l: posts) {
                merged.addAll(l);
        }
        Collections.sort(merged);
        System.out.println("merged list =>\n " +merged);
        return removeDup(merged);
    }

    static List<Post> removeDup(List<Post> posts) {
        List<Post> clean = new ArrayList<>();
        for (Post post : posts) {
            if (!clean.contains(post)) {
                clean.add(post);
            }
        }
        return clean;
    }
}


@Data
class Post implements Comparable<Post> {
    int id;
    String description;
    String image;
    int createdAt;

    Post(int id, String description, String image, int createdAt) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", createdAt=" + createdAt +
                '}'+"\n";
    }

    @Override
    public int compareTo(Post o) {
        if (this.id == o.id) {
            return this.createdAt - o.createdAt;
        }
        return this.id - o.id;
    }
}
