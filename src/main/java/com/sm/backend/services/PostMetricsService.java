package com.sm.backend.services;

import com.sm.backend.entity.PostMetrics;
import com.sm.backend.repository.PostMetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMetricsService {

    @Autowired
    private PostMetricsRepository pmr;

    public PostMetrics createPostMetric(PostMetrics postMetrics) {
        return pmr.save(postMetrics);
    }

    public void updatePostMetric(PostMetrics postMetrics, int id){
        postMetrics.setId(id);
        pmr.save(postMetrics);
    }
}
