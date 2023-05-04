package com.santg.springboot.thymeleafdemo.service;

import com.santg.springboot.thymeleafdemo.entity.VideoData;
import com.santg.springboot.thymeleafdemo.repository.ArticleRepository;
import com.santg.springboot.thymeleafdemo.repository.CourseRepository;
import com.santg.springboot.thymeleafdemo.entity.Article;
import com.santg.springboot.thymeleafdemo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{

    private ArticleRepository articleRepository;
    private CourseRepository courseRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository,CourseRepository courseRepository){
        this.articleRepository = articleRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(new Article());
    }

    @Override
    public void saveArticle(Article article,Long courseId) {
        Course course = courseRepository.getOne(courseId);
        article.setCourse(course);
        articleRepository.saveAndFlush(article);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> getArticlesByCourseId(Long courseId) {
        return articleRepository.findAllByCourseId(courseId);
    }
}
