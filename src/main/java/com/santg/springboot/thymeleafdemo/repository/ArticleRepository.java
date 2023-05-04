package com.santg.springboot.thymeleafdemo.repository;

import com.santg.springboot.thymeleafdemo.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findAllByOrderById();
    List<Article> findAllByCourseId(Long courseId);
}
