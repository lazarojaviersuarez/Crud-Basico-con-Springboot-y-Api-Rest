package com.example.Api.ServiceArticle;

import com.example.Api.Entity.Article;
import com.example.Api.Entity.Person;
import com.example.Api.Repository.ArticleRepo;
import com.example.Api.Repository.Repo;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service

public class ArticleService implements InterfaceArt {
    private HashMap<String,Object> dates;
    private final ArticleRepo articleRepo;
    @Autowired
    public ArticleService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    @Override
    public List<Article> getArticles() {
        return articleRepo.findAll();
    }

    @Override
    public ResponseEntity<Object> createArticle(Article article) {
        Optional<Article> obc=articleRepo.findById(article.getId());
        dates=new HashMap<>();
        if (obc.isPresent()) {
            dates.put("Error", true);
            dates.put("Message", "ya existe este articulo");
            return new  ResponseEntity<>(
                    dates,
                    HttpStatus.CONFLICT
            );
        };
        articleRepo.save(article);
        dates.put("Creado","");
        dates.put("date",article);
        return new ResponseEntity<>(
                dates,
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<Object> updateArticle(Article article) {
        dates=new HashMap<>();
        Optional<Article>obc=articleRepo.findById(article.getId());
        if (obc.isPresent()){
            articleRepo.save(article);
            dates.put("Message","Update succefuly");
            dates.put("data", article);
            return new ResponseEntity<>(
                    dates,HttpStatus.ACCEPTED
            );
        }
        dates.put("Error",true);
        dates.put("Message","not exist el articulo");
        return new ResponseEntity<>(
                dates,HttpStatus.CONFLICT
        );
    }

    @Override
    public ResponseEntity<Object> deleteArticle(long id) {
        dates=new HashMap<>();
        boolean exist=this.articleRepo.existsById(id);
        if (!exist){
            dates.put("Error",true);
            dates.put("message","Is not found");
            return new ResponseEntity<>(
                    dates,
                    HttpStatus.CONFLICT);


        }
        articleRepo.deleteById(id);
        dates.put("message","Delete Succefuly");
        return new ResponseEntity<>(
                dates,
                HttpStatus.ACCEPTED);
    }




}
