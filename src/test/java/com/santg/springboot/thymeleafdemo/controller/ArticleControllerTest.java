package com.santg.springboot.thymeleafdemo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    @Test
    public void contextLoads() {
        assertEquals(true,true);
    }
}