package com.santg.springboot.thymeleafdemo.controller;

import com.santg.springboot.thymeleafdemo.entity.Article;
import com.santg.springboot.thymeleafdemo.entity.Subtitles;
import com.santg.springboot.thymeleafdemo.service.ArticleService;
import com.santg.springboot.thymeleafdemo.service.SubtitlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subtitle")
public class SubtitleController {


    private SubtitlesService subtitlesService;
    private ArticleService articleService;

    @Autowired
    public SubtitleController (SubtitlesService subtitlesService,ArticleService articleService){
        this.subtitlesService = subtitlesService;
        this.articleService = articleService;
    }

    @GetMapping("/add/{id}")
    public String addSubtitle(@PathVariable("id") Long id, Model model){
        Subtitles subtitle = new Subtitles();
        model.addAttribute("subtopic",subtitle);
        model.addAttribute("articleId",id);
        return "subtitle/subtitle-add";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("subtopic") Subtitles subtitle,@RequestParam("articleId") Long id) {
        Article article = articleService.getArticleById(id);
        subtitle.setArticle(article);
        subtitlesService.saveSubtitle(subtitle);
        return "redirect:/articles/details?articleId=" + id;
    }

    @GetMapping("/update/{id}/{subtopicId}")
    public String updateSubtitle(@PathVariable("id") Long id,@PathVariable("subtopicId") long subtopicId, Model model) {
        Subtitles subtitle = subtitlesService.getSubtitleById(subtopicId);
        model.addAttribute("subtopic", subtitle);
        model.addAttribute("articleId", id);
        return "subtitle/subtitle-add";
    }

    @GetMapping("/delete")
    public String deleteSubtitle(@RequestParam("subtopicId") long subtopicId,@RequestParam("articleId") long id) {
        subtitlesService.deleteSubtitles(subtopicId);
        return "redirect:/articles/details?articleId=" + id;
    }
}
