package com.engilyin.bestpractices.rest.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.engilyin.bestpractices.rest.openapi.api.ArticlesApi;
import com.engilyin.bestpractices.rest.openapi.model.Article;
import com.engilyin.bestpractices.rest.openapi.model.PostArticlesRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
