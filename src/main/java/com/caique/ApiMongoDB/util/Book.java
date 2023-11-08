package com.caique.ApiMongoDB.util;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("books")
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private int likes;
    private int dislikes;

    public Book() {}

    public Book(String id, String title, String author, int likes, int dislikes) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}
