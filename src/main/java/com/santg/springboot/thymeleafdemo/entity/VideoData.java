package com.santg.springboot.thymeleafdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "video_data_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;
    private String type;
    private String videoPath;
}
