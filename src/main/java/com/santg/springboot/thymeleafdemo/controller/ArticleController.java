package com.santg.springboot.thymeleafdemo.controller;

import com.santg.springboot.thymeleafdemo.entity.Article;
import com.santg.springboot.thymeleafdemo.entity.VideoData;
import com.santg.springboot.thymeleafdemo.service.ArticleService;
import com.santg.springboot.thymeleafdemo.service.VideoDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    private final VideoDataService videoDataService;

    @GetMapping("/list")
    public String getArticleList(Model model){
        model.addAttribute("articles",articleService.getAllArticles());
        return "article/articles";
    }

    @GetMapping("/add/{id}")
    public String addArticle(@PathVariable("id") Long courseId, Model model) {
        Article article = new Article();
        model.addAttribute("article", article);
        model.addAttribute("courseId", courseId);

        return "article/article-save";
    }

    @PostMapping("/save")
    public String saveArticle(@ModelAttribute("article") Article article,@RequestParam("courseId") Long courseId,
                              @RequestParam(name="videoFile",required = false) MultipartFile multipartFile) {
        if(multipartFile != null){
            try {
                VideoData videoData = videoDataService.save(multipartFile);
                article.setVideoData(videoData);
            } catch (IOException e) {
            }
        }
        articleService.saveArticle(article,courseId);
        return "redirect:/course/details/" + courseId;
    }

    @GetMapping("/update")
    public String updateArticle(@RequestParam("articleId") long id,@RequestParam Long courseId, Model model) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        model.addAttribute("courseId", courseId);
        return "article/article-save";
    }

    @GetMapping("/delete")
    public String deleteArticle(@RequestParam("articleId") long id) {
        articleService.deleteArticle(id);
        return "redirect:/articles/list";
    }

    @GetMapping("/details")
    public String detailsArticle(@RequestParam("articleId") long id,Model model) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "article/article-details";
    }

    @GetMapping("/list/page")
    public String showArticlesByCourse(@RequestParam("courseId") Long courseId,Model model){
        List<Article> articleList = articleService.getArticlesByCourseId(courseId);
        model.addAttribute("articleList",articleList);
        return "article/article-list-page";
    }

    @GetMapping("/show/subtitle")
    public String showArticleForRead(@RequestParam("articleId") Long articleId,Model model){
        Article article = articleService.getArticleById(articleId);
        model.addAttribute("article",article);
        return "article/article-read";
    }
}
