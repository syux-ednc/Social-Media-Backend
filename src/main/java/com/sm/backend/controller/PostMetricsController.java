package com.sm.backend.controller;

import com.sm.backend.entity.PostMetrics;
import com.sm.backend.services.PostMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/postMetrics")
public class PostMetricsController {

    @Autowired
    private PostMetricsService pms;

    @PostMapping("/createPostMetrics")
    public PostMetrics createPostMetrics (@RequestBody PostMetrics postMetrics){
        return pms.createPostMetric(postMetrics);
    }

    @PutMapping("/updatePostMetrics/{id}")
    public void updatePostMetrics(@PathVariable int id, @RequestBody PostMetrics postMetrics){
        pms.updatePostMetric(postMetrics, id);
    }
}
