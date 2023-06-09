package com.santg.springboot.thymeleafdemo.repository;

import com.santg.springboot.thymeleafdemo.entity.Subtitles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtitleRepository extends JpaRepository<Subtitles,Long> {
    List<Subtitles> findAllByOrderById();
}
