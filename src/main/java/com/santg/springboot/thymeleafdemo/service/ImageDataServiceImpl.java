package com.santg.springboot.thymeleafdemo.service;

import com.santg.springboot.thymeleafdemo.entity.ImageData;
import com.santg.springboot.thymeleafdemo.entity.Subtitles;
import com.santg.springboot.thymeleafdemo.repository.ImageDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;

@Service
@Transactional
@Slf4j
public class ImageDataServiceImpl implements ImageDataService{

    private final ImageDataRepository imageDataRepository;
    private final String IMAGE_FOLDER = "C:/Users/User/Desktop/projectsEducationPlatform/SpringBoot-and-Thymeleaf-CRUD-demo/src/main/resources/static/imageData/";
    private final String IMAGE_SRC = "/imageData/";

    @Autowired
    public ImageDataServiceImpl(ImageDataRepository imageDataRepository) {
        this.imageDataRepository = imageDataRepository;
    }

    @Override
    public ImageData save(MultipartFile file) throws IOException {
        String imagePath = IMAGE_FOLDER + file.getOriginalFilename();
        String imageSrc = IMAGE_SRC + file.getOriginalFilename();

        ImageData imageData = imageDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imagePath(imageSrc)
                .build());

        if(imageData != null){
            file.transferTo(new File(imagePath));
            return imageData;
        }
        log.error("Image Data " + imageData.getName() + " don't saved.");
        return null;
    }
}
