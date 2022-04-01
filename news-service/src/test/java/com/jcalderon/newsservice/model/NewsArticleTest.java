package com.jcalderon.newsservice.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewsArticleTest {

    @Test
    void equalsContract() {
        NewsArticle newsArticle = NewsArticle.builder().
                id("id").
                category(NewsCategory.randomCategory()).
                title("title").
                description("description").
                tags(List.of("tag1", "tag2")).
                weight(1).
                build();
        assertEquals(newsArticle, newsArticle);
    }

}