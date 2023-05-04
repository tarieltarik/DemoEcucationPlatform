package com.santg.springboot.thymeleafdemo.service;

import com.santg.springboot.thymeleafdemo.entity.VideoData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoDataService {
    VideoData save(MultipartFile multipartFile) throws IOException;
}
