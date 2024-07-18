package com.example.Api.Repository;

import com.example.Api.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ArticleRepo extends JpaRepository<Article,Long> {
    Optional<Article>findById(long id);
}
