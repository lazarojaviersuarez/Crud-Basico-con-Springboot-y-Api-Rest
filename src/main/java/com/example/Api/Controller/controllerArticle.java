package com.example.Api.Controller;

import com.example.Api.Entity.Article;
import com.example.Api.Entity.Person;
import com.example.Api.ServiceArticle.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/Articulo")
public class controllerArticle {
    private final ArticleService articleService;

    @GetMapping
    public List<Article> Get( ){
        return this.articleService.getArticles();

    }

    @PostMapping("/createArt")
    public ResponseEntity<Object> NewPerson(@RequestBody Article article){
        return this.articleService.createArticle(article);
    }
    @PutMapping("/updateArt")
    public ResponseEntity<Object>UpdatePerson(@RequestBody Article article){
        return this.articleService.updateArticle(article);
    }
    @DeleteMapping(path = "{Articleid}")
    public ResponseEntity<Object>DeletePerson(@PathVariable ("Articleid") int id){
        return this.articleService.deleteArticle(id);
    }
}
