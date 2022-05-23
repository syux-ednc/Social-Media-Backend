package com.sm.backend.services;

import com.sm.backend.entity.Post;
import com.sm.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostService {

    @Autowired
    private PostRepository pr;

    public List<Post> getAllPosts() {
        return pr.findAll();
    }

    public Page<Post> getAllPostsWithPagination(int offset, int pageSize, String sortBy) {
        return pr.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.DESC, sortBy)));
    }

    public Page<Post> getAllPostsByUserWithPagination(String createdBy, int offset, int pageSize, String sortBy) {
        return pr.findAllByCreatedBy(
                PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.DESC, sortBy)),
                createdBy
        );
    }

    public Optional<Post> getPostById(int id) {
        return pr.findById(id);
    }

    public Post createPost(Post post) {
        return pr.save(post);
    }

    public void deletePostById(int id) {
        pr.deleteById(id);
    }

    public void updatePost(Post post, int id) {
        post.setId(id);
        pr.save(post);
    }
}
