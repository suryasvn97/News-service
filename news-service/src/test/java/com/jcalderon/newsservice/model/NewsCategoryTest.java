package com.jcalderon.newsservice.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class NewsCategoryTest {

    @Test
    void randomCategory() {
        List<String> newsCategories = Arrays.stream(NewsCategory.values()).map(NewsCategory::toString).collect(Collectors.toList());
        assertTrue(newsCategories.contains(NewsCategory.randomCategory()));
    }
}