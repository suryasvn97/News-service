package com.jcalderon.newsservice.service;

import com.jcalderon.newsservice.model.NewsCategory;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import com.jcalderon.newsservice.model.NewsArticle;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class NewsService {

    private static final int MIN_WEIGHT = 1;
    private static final int MAX_WEIGHT = 20;
    private final Lorem lorem = LoremIpsum.getInstance();

    public List<NewsArticle> getNews() {
        return List.of(generateRandomNews(),
                generateRandomNews(),
                generateRandomNews(),
                generateRandomNews(),
                generateRandomNews(),
                generateRandomNews(),
                generateRandomNews());
    }

    public List<NewsArticle> downloadFile(List<NewsArticle> articleList) {
        List<NewsArticle> finalList=new ArrayList<>();
        for (NewsArticle item : articleList) {
           if(item.getCategory()!="SOCIAL") {
               finalList.add(NewsArticle.builder().
                       id(StringUtils.right(item.getId(), 10)).
                       category(item.getCategory()).
                       title(item.getTitle()).
                       tags(item.getTags()).
                       weight(item.getWeight()).
                       build());
           }
        }
        finalList.sort(Comparator.comparing(NewsArticle::getWeight).reversed());
        return finalList;

    }

    private NewsArticle generateRandomNews(){
        return NewsArticle.builder().
                id(UUID.randomUUID().toString()).
                category(NewsCategory.randomCategory()).
                title(lorem.getTitle(2, 6)).
                description(lorem.getWords(3, 20)).
                tags(List.of(getRandomTag(), getRandomTag())).
                weight(getRandomWeighting()).
                build();
    }

    private int getRandomWeighting() {
        return ThreadLocalRandom.current().nextInt(MIN_WEIGHT, MAX_WEIGHT + 1);
    }

    private String getRandomTag() {
        return lorem.getWords(1);
    }
}
