package com.engilyin.bestpractices.rest.controllers;

import com.engilyin.bestpractices.rest.openapi.api.ArticlesApi;
import com.engilyin.bestpractices.rest.openapi.model.Article;
import com.engilyin.bestpractices.rest.openapi.model.PostArticlesRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ArticleController implements ArticlesApi {

    @Override
    public ResponseEntity<List<Article>> getArticles() {
        return null;
    }

    @Override
    public ResponseEntity<Void> postArticles(@Valid PostArticlesRequest postArticlesRequest) {
        return null;
    }
}
