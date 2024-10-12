package com.engilyin.bestpractices.rest.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import com.engilyin.bestpractices.rest.config.ArticleRequestTestConfig;
import com.engilyin.bestpractices.rest.openapi.model.PostArticlesRequest;

import io.restassured.module.mockmvc.RestAssuredMockMvc;



@ExtendWith(SpringExtension.class)
@WebMvcTest(
	controllers = ArticleController.class,
	excludeAutoConfiguration = {SecurityAutoConfiguration.class}
	)
@ContextConfiguration(
	classes = {ArticleController.class, ArticleRequestTestConfig.class}
	)
public class ArticleControllerIntegrationTest {
    
    @Autowired
    WebApplicationContext context;
    
    @BeforeEach
    void initStandaloneRestasure() {
	RestAssuredMockMvc.webAppContextSetup(context);
    }
    
    @Test
    void normal_create_articles() {
	var newArticle = new PostArticlesRequest();
	
	given()
	 .contentType(APPLICATION_JSON_VALUE)
	 .body(newArticle)
	 .header("test", "1")
	 .when()
	 .post("/articles")
	 .then()
	 .log()
	 .everything()
	 .statusCode(OK.value())
	 .body("test.best", equalTo("hello"));
	
    }

}
