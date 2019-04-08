package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ArticleAuthor {
    private int articleAuthorId;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "article_author_id", nullable = false)
    public int getArticleAuthorId() {
        return articleAuthorId;
    }

    public void setArticleAuthorId(int articleAuthorId) {
        this.articleAuthorId = articleAuthorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleAuthor that = (ArticleAuthor) o;
        return articleAuthorId == that.articleAuthorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleAuthorId);
    }
}
