package com.santg.springboot.thymeleafdemo.repository;

import com.santg.springboot.thymeleafdemo.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileEntityRepository extends JpaRepository<FileEntity,Long> {
}
