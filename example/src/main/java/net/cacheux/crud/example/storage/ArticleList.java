package net.cacheux.crud.example.storage;

import net.cacheux.crud.example.model.Article;

public class ArticleList extends ItemList<Article> {
    private static ArticleList instance = new ArticleList();

    private ArticleList() {

    }

    public static ArticleList getInstance() {
        return instance;
    }
}
