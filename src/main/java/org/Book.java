package org;

import java.time.LocalDate;
//import java.util.Date;

public class Book {

    private String title;
    private String author;
    private LocalDate published;

    @Override
    public String toString() {
        return "org.Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", published=" + published +
                '}';
    }

    public Book(String title, String author, LocalDate published) {
        super();
        this.title = title;
        this.author = author;
        this.published = published;
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

    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

}