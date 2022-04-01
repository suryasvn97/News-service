package com.jcalderon.newsservice.api;

import com.jcalderon.newsservice.model.NewsArticle;
import com.jcalderon.newsservice.service.NewsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewsControllerTest {

    @InjectMocks
    private NewsController newsController;

    @Mock
    private NewsService newsService;


    @Test
    void searchRequest() {
        when(newsService.getNews()).thenReturn(Collections.EMPTY_LIST);

        ResponseEntity<List<NewsArticle>> responseEntity = newsController.search();

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void downloadFile() throws FileNotFoundException {
        when(newsService.getNews()).thenReturn(Collections.EMPTY_LIST);

        when(newsService.downloadFile(any(List.class))).thenReturn(Collections.EMPTY_LIST);

        ResponseEntity<Resource> responseEntity = newsController.downloadFile();

        assertEquals(200, responseEntity.getStatusCodeValue());
    }


}