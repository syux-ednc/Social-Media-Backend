package com.sm.backend.repository;

import com.sm.backend.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Page<Post> findAllByCreatedBy(Pageable pageable, String createdBy);
}
