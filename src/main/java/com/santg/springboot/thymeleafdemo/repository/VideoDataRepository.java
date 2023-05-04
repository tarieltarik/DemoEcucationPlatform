package com.santg.springboot.thymeleafdemo.repository;

import com.santg.springboot.thymeleafdemo.entity.Article;
import com.santg.springboot.thymeleafdemo.entity.VideoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoDataRepository extends JpaRepository<VideoData,Long> {
    Optional<VideoData> findByName(String videoName);
}
