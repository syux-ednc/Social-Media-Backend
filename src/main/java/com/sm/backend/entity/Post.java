package com.sm.backend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post extends AuditMetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "caption")
    private String caption;

    @Column(name = "media_src")
    private String media_src;

    @Column(name = "user_id")
    private int user_id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<PostMetrics> postMetrics;

    public Post() {
    }

    public Post(int id, String type, String caption, String media_src, int user_id) {
        this.id = id;
        this.type = type;
        this.caption = caption;
        this.media_src = media_src;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getMedia_src() {
        return media_src;
    }

    public void setMedia_src(String media_src) {
        this.media_src = media_src;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<PostMetrics> getPostMetrics() {
        return postMetrics;
    }

    public void setPostMetrics(List<PostMetrics> postMetrics) {
        this.postMetrics = postMetrics;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", caption='" + caption + '\'' +
                ", media_src='" + media_src + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
