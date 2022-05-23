package com.sm.backend.controller;

import com.sm.backend.entity.Post;
import com.sm.backend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService ps;

    @GetMapping("/getPosts")
    public List<Post> getPosts() {
        return ps.getAllPosts();
    }

    @GetMapping("/pagination/{offset}/{pageSize}/{sortBy}")
    public Page<Post> getPostsWithPaginationAndSort(
            @PathVariable int offset,
            @PathVariable int pageSize,
            @PathVariable String sortBy
    ) {
        return ps.getAllPostsWithPagination(offset, pageSize, sortBy);
    }

    @GetMapping("/getPostsByUserWithPagination/{user}/{offset}/{pageSize}/{sortBy}")
    public Page<Post> getPostsByUserWithPagination(
            @PathVariable String user,
            @PathVariable int offset,
            @PathVariable int pageSize,
            @PathVariable String sortBy
    ) {
        return ps.getAllPostsByUserWithPagination(user, offset, pageSize, sortBy);
    }

    @GetMapping("/getPost/{id}")
    public Optional<Post> getPostById(@PathVariable int id) {
        return ps.getPostById(id);
    }

    @PostMapping("/createPost")
    public Post createPost(@RequestBody Post post) {
        return ps.createPost(post);
    }

    @DeleteMapping("/deletePost/{id}")
    public void deletePostById(@PathVariable("id") int id) {
        ps.deletePostById(id);
    }

    @PutMapping("/updatePost/{id}")
    public void updatePost(@PathVariable("id") int id, @RequestBody Post post) {
        ps.updatePost(post, id);
    }
}
