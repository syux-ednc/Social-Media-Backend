package com.sm.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "posts_metrics")
public class PostMetrics extends AuditMetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private int id;

    @Column()
    private int views;

    @Column()
    private int post_id;

    public PostMetrics(){}

    public PostMetrics(int id, int views, int post_id) {
        this.id = id;
        this.views = views;
        this.post_id = post_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    @Override
    public String toString() {
        return "PostMetrics{" +
                "id=" + id +
                ", views=" + views +
                ", post_id=" + post_id +
                '}';
    }
}
