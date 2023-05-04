package com.santg.springboot.thymeleafdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="subtitles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subtitles {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="article_id", nullable=false)
    private Article article;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="image_id")
    private ImageData imageData;
}
