package com.santg.springboot.thymeleafdemo.service;

import com.santg.springboot.thymeleafdemo.entity.VideoData;
import com.santg.springboot.thymeleafdemo.repository.VideoDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class VideoDataServiceImpl implements VideoDataService {

    private final VideoDataRepository videoDataRepository;

    private final String FOLDER_PATH_VIDEO="C:/Users/User/Desktop/projectsEducationPlatform/SpringBoot-and-Thymeleaf-CRUD-demo/src/main/resources/static/VideoData/";
    private final String VIDEO_SRC="/VideoData/";

    @Override
    public VideoData save(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH_VIDEO+file.getOriginalFilename();
        String videoSrc = VIDEO_SRC + file.getOriginalFilename();

        VideoData videoData = videoDataRepository.save(VideoData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .videoPath(videoSrc)
                .build());

        file.transferTo(new File(filePath));

        if(videoData == null){
            throw new FileNotFoundException("video file not found");
        }
        return videoData;
    }
}
