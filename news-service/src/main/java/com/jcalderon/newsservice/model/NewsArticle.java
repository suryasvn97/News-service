package com.jcalderon.newsservice.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public final class NewsArticle {

    private final String id;
    private final String category;
    private final String title;
    private final String description;
    private final List<String> tags;
    private final int weight;

}
