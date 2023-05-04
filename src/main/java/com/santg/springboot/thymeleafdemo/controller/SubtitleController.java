package com.santg.springboot.thymeleafdemo.controller;

import com.santg.springboot.thymeleafdemo.entity.Article;
import com.santg.springboot.thymeleafdemo.entity.ImageData;
import com.santg.springboot.thymeleafdemo.entity.Subtitles;
import com.santg.springboot.thymeleafdemo.service.ArticleService;
import com.santg.springboot.thymeleafdemo.service.ImageDataService;
import com.santg.springboot.thymeleafdemo.service.SubtitlesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/subtitle")
@Slf4j
public class SubtitleController {


    private final SubtitlesService subtitlesService;
    private final ArticleService articleService;
    private final ImageDataService imageDataService;

    @Autowired
    public SubtitleController(SubtitlesService subtitlesService, ArticleService articleService, ImageDataService imageDataService) {
        this.subtitlesService = subtitlesService;
        this.articleService = articleService;
        this.imageDataService = imageDataService;
    }

    @GetMapping("/add/{id}")
    public String addSubtitle(@PathVariable("id") Long id, Model model){
        Subtitles subtitle = new Subtitles();
        model.addAttribute("subtopic",subtitle);
        model.addAttribute("articleId",id);
        return "subtitle/subtitle-add";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("subtopic") Subtitles subtitle, @RequestParam("articleId") Long id,
                               @RequestParam(name="imageFile",required = false)MultipartFile multipartFile,Model model) {
        Article article = articleService.getArticleById(id);
        subtitle.setArticle(article);

        //if file not null

        if(multipartFile != null){
            try {
                ImageData imageData = imageDataService.save(multipartFile);
                subtitle.setImageData(imageData);
            } catch (IOException e) {
                log.error(e.getLocalizedMessage());
            }
        }
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
