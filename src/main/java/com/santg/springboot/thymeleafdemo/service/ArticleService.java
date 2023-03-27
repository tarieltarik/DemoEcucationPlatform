package com.santg.springboot.thymeleafdemo.service;

import com.santg.springboot.thymeleafdemo.entity.Article;

import java.util.List;

public interface ArticleService {
    public List<Article> getAllArticles();
    public Article getArticleById(Long id);
    public void saveArticle(Article article,Long courseId);
    public void deleteArticle(Long id);
    public List<Article> getArticlesByCourseId(Long courseId);
}
