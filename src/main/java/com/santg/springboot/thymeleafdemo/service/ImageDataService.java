package com.santg.springboot.thymeleafdemo.service;

import com.santg.springboot.thymeleafdemo.entity.Article;
import com.santg.springboot.thymeleafdemo.entity.ImageData;
import com.santg.springboot.thymeleafdemo.entity.Subtitles;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageDataService {

    ImageData save(MultipartFile file) throws IOException;
}
