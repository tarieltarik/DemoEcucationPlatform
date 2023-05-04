package com.santg.springboot.thymeleafdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.List;

@Entity
@Table(name="article")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    private Course course;

    @OneToMany(mappedBy="article",cascade = CascadeType.ALL)
    private List<Subtitles> subtitlesList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="video_id")
    private VideoData videoData;
}
