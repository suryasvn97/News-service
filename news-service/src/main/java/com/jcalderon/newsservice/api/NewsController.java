package com.jcalderon.newsservice.api;

import com.jcalderon.newsservice.model.NewsArticle;
import com.jcalderon.newsservice.service.NewsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/news")
    @ApiOperation(value = "Returns a random list of news articles",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NewsArticle>> search() {
        log.debug("Requesting news...");
        return ResponseEntity.ok(newsService.getNews());
    }

    @GetMapping("/download")
    @ApiOperation(value = "Downloads File")
    public ResponseEntity<Resource> downloadFile() throws FileNotFoundException {
        File file2Upload = null;
        HttpHeaders headers = null;
        InputStreamResource resource= null;
        List<NewsArticle> articleList = newsService.getNews();
        List<NewsArticle> finalList = newsService.downloadFile(articleList);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("edited_articles.txt"), "utf-8"))) {
            for (NewsArticle item : finalList) {
                writer.write("id:" + item.getId() + ",category:" + item.getCategory() + ",Title:" + item.getTitle() + ",Number of Tags:"+item.getTags() + ",Weight:"+item.getWeight());
                writer.write("\n");
                writer.write("===============================================================================");
                writer.write("\n");
            }
            file2Upload = new File("edited_articles.txt");
            headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-disposition", "attachment; filename=edited_articles.txt");
            resource = new InputStreamResource(new FileInputStream(file2Upload));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file2Upload.length())
                .contentType(MediaType.TEXT_PLAIN)
                .body(resource);
    }
}
