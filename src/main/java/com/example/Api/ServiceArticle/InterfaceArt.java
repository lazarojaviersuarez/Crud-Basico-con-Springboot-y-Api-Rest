package com.example.Api.ServiceArticle;

import com.example.Api.Entity.Article;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface InterfaceArt {
    List<Article>getArticles();
    ResponseEntity<Object>createArticle(Article article);
    ResponseEntity<Object>updateArticle(Article article);
    ResponseEntity<Object>deleteArticle(long id);
}
