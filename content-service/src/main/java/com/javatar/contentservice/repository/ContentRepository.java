package com.javatar.contentservice.repository;

import com.javatar.contentservice.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
    
    Optional<Content> findByReviewid(Integer reviewid);
}
