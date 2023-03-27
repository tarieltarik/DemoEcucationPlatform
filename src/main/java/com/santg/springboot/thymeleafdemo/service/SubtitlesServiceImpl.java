package com.santg.springboot.thymeleafdemo.service;

import com.santg.springboot.thymeleafdemo.dao.SubtitleRepository;
import com.santg.springboot.thymeleafdemo.entity.Subtitles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtitlesServiceImpl implements SubtitlesService{

    SubtitleRepository subtitleRepository;

    @Autowired
    public SubtitlesServiceImpl(SubtitleRepository subtitleRepository){
        this.subtitleRepository = subtitleRepository;
    }

    @Override
    public List<Subtitles> getAllSubtitles() {
        return subtitleRepository.findAll();
    }

    @Override
    public Subtitles getSubtitleById(Long id) {
        return subtitleRepository.findById(id).orElse(new Subtitles());
    }

    @Override
    public Subtitles saveSubtitle(Subtitles subtitles) {
        return subtitleRepository.save(subtitles);
    }

    @Override
    public void deleteSubtitles(Long id) {
        subtitleRepository.deleteById(id);
    }
}
