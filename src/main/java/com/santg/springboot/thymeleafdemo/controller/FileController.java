package com.santg.springboot.thymeleafdemo.controller;

import com.santg.springboot.thymeleafdemo.entity.FileEntity;
import com.santg.springboot.thymeleafdemo.repository.FileEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/doc")
@RequiredArgsConstructor
public class FileController {
    private final FileEntityRepository fileEntityRepository;

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        model.addAttribute("fileEntity", new FileEntity());
        return "filePage";
    }

    @PostMapping("/upload")
    public String uploadFile(@ModelAttribute FileEntity fileEntity,
                             @RequestParam(name = "file_input") MultipartFile file) throws IOException {
        fileEntity.setFilename(file.getOriginalFilename());
        fileEntity.setData(file.getBytes());
        fileEntityRepository.save(fileEntity);
        return "redirect:/doc/list";
    }

    @GetMapping("/list")
    public String fileList(Model model){
        model.addAttribute("fileList",fileEntityRepository.findAll());
        return "fileListPage";
    }

    @GetMapping("/download")
    public void downloadFile(@RequestParam("fileId") long id, HttpServletResponse response) throws IOException {
        FileEntity fileEntity = fileEntityRepository.findById(id).orElseGet(null);

        response.setContentType("application/msword");
        response.setContentLength(fileEntity.getData().length);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileEntity.getFilename() + "\"");

        FileCopyUtils.copy(fileEntity.getData(), response.getOutputStream());
    }

    @GetMapping("/delete")
    public String fileDelete(@RequestParam("fileId") long id){
        fileEntityRepository.deleteById(id);
        return "redirect:/doc/list";
    }
}

