package com.engilyin.bestpractices.rest.config;

import com.engilyin.bestpractices.rest.openapi.model.PostArticlesRequest;
import com.engilyin.bestpractices.rest.utils.TestSampleLoader;
import java.util.Map;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ArticleRequestTestConfig {

    @Bean
    public Map<String, PostArticlesRequest> sampleNewArticles() {
        var loader = new TestSampleLoader<>(PostArticlesRequest.class);

        return loader.load("new-articles.json");
    }
}
