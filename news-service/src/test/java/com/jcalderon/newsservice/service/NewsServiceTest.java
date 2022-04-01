package com.jcalderon.newsservice.service;

import com.jcalderon.newsservice.model.NewsArticle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsServiceTest {

    private NewsService newsService;

    @BeforeEach
    public void setUp() {
        newsService = new NewsService();
    }

    @Test
    void shouldFakeNews() {
        List<NewsArticle> news = newsService.getNews();
        assertEquals(7, news.size());
    }

    @Test
    void downloadFile(){
        List<NewsArticle> naList = newsService.getNews();
        List<NewsArticle> finalList = newsService.downloadFile(naList);
        List<String> categoryList = new ArrayList<String>();
        for(NewsArticle na:finalList){
            categoryList.add(na.getCategory());
        }
        assertFalse(categoryList.contains("SOCIAL"));
    }

}