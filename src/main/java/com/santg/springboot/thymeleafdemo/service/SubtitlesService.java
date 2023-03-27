package com.santg.springboot.thymeleafdemo.service;

import com.santg.springboot.thymeleafdemo.entity.Subtitles;

import java.util.List;

public interface SubtitlesService {
    public List<Subtitles> getAllSubtitles();
    public Subtitles getSubtitleById(Long id);
    public Subtitles saveSubtitle(Subtitles subtitles);
    public void deleteSubtitles(Long id);
}
