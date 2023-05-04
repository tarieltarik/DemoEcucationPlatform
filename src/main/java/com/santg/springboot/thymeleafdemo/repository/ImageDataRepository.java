package com.santg.springboot.thymeleafdemo.repository;

import com.santg.springboot.thymeleafdemo.entity.ImageData;
import com.santg.springboot.thymeleafdemo.entity.Subtitles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageDataRepository extends JpaRepository<ImageData,Long> {
    Optional<ImageData> findByName(String imageName);
}
